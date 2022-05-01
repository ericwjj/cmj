package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.OrderFormMapper;
import com.soft2146.crm.api.mapper.PerformanceMapper;
import com.soft2146.crm.api.model.entity.*;
import com.soft2146.crm.api.model.vo.CustomerOrdersVo;
import com.soft2146.crm.api.model.vo.OrderVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-14 08:34
 **/
@Service
public class OrderFormService extends ServiceImpl<OrderFormMapper, OrderForm> {
    @Resource
    private ProductService productService;
    @Resource
    private PerformanceService performanceService;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private CustomerService customerService;
    @Resource
    private PerformanceMapper performanceMapper;


    public Result getOrderListByPage(Integer pageNo, Integer pageSize) {
        Page<OrderVo> page = new Page<>(pageNo, pageSize);
        IPage<OrderVo> orderVoIPage = baseMapper.getOrderListByPage(page);
        return Result.success(orderVoIPage);
    }

    public Result deleteBathOrder(List<Integer> ids) {
        baseMapper.deleteBathOrder(ids);
        return Result.success();
    }

    public Result addOrder(OrderForm order) {
        Product product = productService.lambdaQuery().eq(Product::getId, order.getProductId())
                .eq(Product::getDeleteFlag, false).one();
        Customer customer = customerService.lambdaQuery().eq(Customer::getId, order.getCustomerId())
                .eq(Customer::getDeleteFlag, false).one();
        // 用户信用度低于80 不给下单权限
        if (customer.getCredit() >= 80) {
            if (product != null) {
                // 判断库存的数量是否大于下单的数量
                if (product.getRepertory() > order.getNumber()) {
                    // 计算订单的总金额
                    Double orderMoney = order.getNumber() * product.getPrice();
                    // 新增订单
                    order.setDeleteFlag(false);
                    order.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                    saveOrUpdate(order);
                    // 新增员工业绩，若该员工本月已经存在业绩那么在当前业绩上进行修改
                    Calendar calendar = Calendar.getInstance();
                    String year = String.valueOf(calendar.get(Calendar.YEAR));
                    String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
                    String yearMonth = year + month;
//                Performance performance = performanceService.lambdaQuery()
//                        .eq(Performance::getEmployeeId, order.getEmployeeId())
//                        .eq(Performance::getYearMonth, yearMonth)
//                        .eq(Performance::getDeleteFlag, false).one();
                    Performance performance = performanceMapper.selectPer(order.getEmployeeId(), yearMonth);
                    // 若当月员工有业绩
                    if (performance != null) {
                        performance.setOrderNumber(performance.getOrderNumber() + 1);
                        performance.setTotalMoney(performance.getTotalMoney() + orderMoney);
                        performance.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
                        performanceService.saveOrUpdate(performance);
                    } else {
                        // 员工当月无业绩
                        Employee employee = employeeService.lambdaQuery().eq(Employee::getId, order.getEmployeeId())
                                .eq(Employee::getDeleteFlag, false).one();
                        Performance savePer = new Performance();
                        savePer.setEmployeeId(order.getEmployeeId());
                        savePer.setEmployeeName(employee.getEmployeeName());
                        savePer.setOrderNumber(1);
                        savePer.setTotalMoney(orderMoney);
                        savePer.setNowTime(yearMonth);
                        savePer.setDeleteFlag(false);
                        savePer.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
                        performanceService.saveOrUpdate(savePer);
                    }
                    // 扣除响应产品的库存数量
                    product.setRepertory(product.getRepertory() - order.getNumber());
                    product.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
                    productService.saveOrUpdate(product);
                    return Result.success("add success");
                } else {
                    return Result.failure(ResultCode.PRODUCT_REPERTORY_INSUFFICIENT);
                }
            } else {
                return Result.failure(ResultCode.PRODUCT_NOT_FOUNT);
            }
        } else {
            return Result.failure(ResultCode.CUSTOMER_CREDIT_LOW);
        }
    }


    public Result selectOrdersByCustomer(Integer customerId) {
        List<CustomerOrdersVo> customerOrdersVos = baseMapper.selectOrdersByCustomer(customerId);
        return Result.success(customerOrdersVos);
    }

    public Result chargeback(Integer orderId) {
        // 1.查询订单是否存在
        OrderForm orderForm = lambdaQuery().eq(OrderForm::getId, orderId)
                .eq(OrderForm::getDeleteFlag, false).one();
        if (orderForm != null) {
            // 2.删除该条订单
            orderForm.setDeleteFlag(true);
            orderForm.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            saveOrUpdate(orderForm);
            // 3.返还商品库存
            Product product = productService.lambdaQuery().eq(Product::getId, orderForm.getProductId())
                    .eq(Product::getDeleteFlag, false).one();
            product.setRepertory(product.getRepertory() + orderForm.getNumber());
            product.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            productService.saveOrUpdate(product);
            // 4.去除员工业绩
            String orderTime = String.valueOf(orderForm.getCreateTime());
            String yearMonth = orderTime.substring(0, 4) + orderTime.substring(5, 7);
            System.out.println("当前年月：" + yearMonth);
            Performance performance = performanceMapper.selectPer(orderForm.getEmployeeId(), yearMonth);
            double orderMoney = orderForm.getNumber() * product.getPrice();
            performance.setTotalMoney(performance.getTotalMoney() - orderMoney);
            performance.setOrderNumber(performance.getOrderNumber() - 1);
            performance.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            performanceService.saveOrUpdate(performance);
            // 5.降低客户信用度,每次退款降低5点信用度,信用度低于80无法进行下单
            // 信用度每个月恢复5点
            Customer customer = customerService.lambdaQuery()
                    .eq(Customer::getId, orderForm.getCustomerId())
                    .eq(Customer::getDeleteFlag, false).one();
            customer.setCredit(customer.getCredit() - 5);
            customer.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            customerService.saveOrUpdate(customer);
            return Result.success("退款成功");
        } else {
            return Result.failure(ResultCode.ORDER_NOT_FOUNT);
        }
    }
}
