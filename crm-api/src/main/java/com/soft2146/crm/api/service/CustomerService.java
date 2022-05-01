package com.soft2146.crm.api.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.CustomerMapper;
import com.soft2146.crm.api.model.dto.SignDto;
import com.soft2146.crm.api.model.entity.Customer;
import com.soft2146.crm.api.model.vo.CuAddVo;
import com.soft2146.crm.api.model.vo.CustomerVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-13 18:09
 **/
@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> {
    public Result getCustomerList() {
        List<Customer> customers = lambdaQuery().eq(Customer::getDeleteFlag, false).list();
        return Result.success(customers);
    }

    public Result getThreeMonthNum() {
        List<CuAddVo> cuAddVos = baseMapper.getThreeMonthNum();
        return Result.success(cuAddVos);
    }

    public Result addCustomer(Customer customer) {
        customer.setCredit(100);
        customer.setPassword(DigestUtils.md5Hex("123456"));
        customer.setDeleteFlag(false);
        customer.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        saveOrUpdate(customer);
        return Result.success("add success");
    }

    public Result deleteBathCustomer(List<Integer> ids) {
        baseMapper.deleteBathCustomer(ids);
        return Result.success();
    }


    public Result updateCustomer(Customer customer) {
        Customer customer1 = lambdaQuery().eq(Customer::getId, customer.getId())
                .eq(Customer::getDeleteFlag, false).one();
        customer1.setAddress(customer.getAddress());
        customer1.setCuName(customer.getCuName());
        customer1.setPhone(customer.getPhone());
        customer1.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        saveOrUpdate(customer1);
        return Result.success("add success");
    }

    public Result login(SignDto signDto) {
        String phone = signDto.getPhone();
        String password = signDto.getPassword();
        Customer customer = lambdaQuery().eq(Customer::getPhone, phone)
                .eq(Customer::getDeleteFlag, false).one();
        if (customer != null) {
            if (DigestUtils.md5Hex(password).equals(customer.getPassword())) {
                StpUtil.login(customer.getId());
                String token = StpUtil.getTokenValue();
                CustomerVo customerVo = CustomerVo.builder()
                        .id(customer.getId())
                        .address(customer.getAddress())
                        .phone(customer.getPhone())
                        .credit(customer.getCredit())
                        .cuName(customer.getCuName())
                        .token(token)
                        .build();
                return Result.success(customerVo);
            }
            return Result.failure(ResultCode.USER_PASSWORD_ERROR);
        }
        return Result.failure(ResultCode.USER_ACCOUNT_ERROR);

    }
}
