package com.soft2146.crm.api.controller;

import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.dto.PlanDto;
import com.soft2146.crm.api.model.entity.DepartmentEmployee;
import com.soft2146.crm.api.model.entity.Employee;
import com.soft2146.crm.api.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 13:30
 **/
@RestController
@RequestMapping(value = "/emp")
@Api(value = "EmployeeController", tags = {"普通员工模块"})
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @GetMapping()
    @ApiOperation("分页查询所有员工信息")
    public Result getEmployeeList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
//        Page page = new Page<>(pageNo, pageSize);
//        return Result.success(employeeService.lambdaQuery().eq(Employee::getDeleteFlag, false)
//                .orderByAsc(Employee::getId).page(page));

        return employeeService.getEmployeeListByPage(pageNo, pageSize);
    }

    @DeleteMapping
    @ApiOperation("批量删除员工")
    public Result deleteBathEmployee(@RequestBody List<Integer> ids) {
        return employeeService.deleteBathEmployee(ids);
    }

    @PostMapping
    @ApiOperation("新增员工")
    public Result addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    @ApiOperation("修改员工信息")
    public Result updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @PostMapping("/dep")
    @ApiOperation(value = "分配员工到部门", notes = "需要部门id和员工id")
    public Result insertEmpToDep(@RequestBody DepartmentEmployee departmentEmployee) {
        return employeeService.insertEmpToDep(departmentEmployee);
    }

    @PostMapping("/plan")
    @ApiOperation(value = "给员工分配计划", notes = "请求参数：员工id、客户id、计划利润、计划时间")
    public Result insertPlanToDep(@RequestBody PlanDto planDto) {
        return employeeService.insertPlanToDep(planDto);
    }

    @GetMapping("/pull")
    @ApiOperation("查询员工列表(下拉列表)")
    public Result getEmployeeList() {
        return employeeService.getEmployeeList();
    }

    @GetMapping("/three")
    @ApiOperation("近三个月员工增长数量")
    public Result getThreeMonthNum() {
        return employeeService.getThreeMonthNum();
    }



}
