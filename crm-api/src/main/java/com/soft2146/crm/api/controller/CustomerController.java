package com.soft2146.crm.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.dto.SignDto;
import com.soft2146.crm.api.model.entity.Customer;
import com.soft2146.crm.api.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-13 18:10
 **/
@RestController
@RequestMapping(value = "/customer")
@Api(value = "CustomerController", tags = {"客户模块"})
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @GetMapping()
    @ApiOperation("分页查询所有客户")
    public Result getCustomerListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        return Result.success(customerService.lambdaQuery().eq(Customer::getDeleteFlag, false)
                .orderByAsc(Customer::getId).page(page));
    }

    @ApiOperation(value = "新增客户", notes = "需要客户名称、手机号码、地址字段")
    public Result addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }


    @DeleteMapping
    @ApiOperation("批量删除客户")
    public Result deleteBathCustomer(@RequestBody List<Integer> ids) {
        return customerService.deleteBathCustomer(ids);
    }

    @PutMapping
    @ApiOperation(value = "修改客户信息", notes = "需要id、手机号码、密码、地址、信用度")
    public Result updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }


    @GetMapping("/pull")
    @ApiOperation("查询客户列表(下拉列表)")
    public Result getCustomerList() {
        return customerService.getCustomerList();
    }

    @GetMapping("/three")
    @ApiOperation("近三个月客户增长数量")
    public Result getThreeMonthNum() {
        return customerService.getThreeMonthNum();
    }

    @ApiOperation("客户帐号密码登录")
    @PostMapping(value = "/login")
    public Result login(@RequestBody SignDto signDto) {
        return customerService.login(signDto);
    }

    @PostMapping
    @ApiOperation("新增客户")
    public Result addEmployee(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }


}
