package com.soft2146.crm.api.controller;

import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.entity.OrderForm;
import com.soft2146.crm.api.service.OrderFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-14 08:35
 **/
@RestController
@RequestMapping(value = "/order")
@Api(value = "OrderController", tags = {"订单模块"})
public class OrderFormController {
    @Resource
    private OrderFormService orderService;

    @GetMapping
    @ApiOperation("分页查询所有订单")
    public Result getOrderListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return orderService.getOrderListByPage(pageNo, pageSize);
    }

    @DeleteMapping
    @ApiOperation("批量删除订单")
    public Result deleteBathOrder(@RequestBody List<Integer> ids) {
        return orderService.deleteBathOrder(ids);
    }


    @PostMapping
    @ApiOperation(value = "客户下单", notes = "需要字段：客户id,产品id,订购数量,联系人id(员工id)")
    public Result addOrder(@RequestBody OrderForm order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/chargeback")
    @ApiOperation("客户退单")
    public Result chargeback(@RequestParam(name = "orderId", defaultValue = "1") Integer orderId) {
        return orderService.chargeback(orderId);
    }

    @GetMapping("/list")
    @ApiOperation("根据客户id查询该客户的下单列表")
    public Result selectOrdersByCustomer(@RequestParam(name = "customerId", defaultValue = "1") Integer customerId) {
        return orderService.selectOrdersByCustomer(customerId);
    }


}
