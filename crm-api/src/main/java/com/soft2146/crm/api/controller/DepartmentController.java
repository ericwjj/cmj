package com.soft2146.crm.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.entity.Department;
import com.soft2146.crm.api.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 14:35
 **/
@RestController
@RequestMapping(value = "/dep")
@Api(value = "DepartmentController", tags = {"部门模块"})
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @GetMapping()
    @ApiOperation("分页查询所有部门")
    public Result getDepartmentListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        return Result.success(departmentService.lambdaQuery().eq(Department::getDeleteFlag, false)
                .orderByAsc(Department::getId).page(page));
    }

    @GetMapping("/list")
    @ApiOperation("查询所有部门列表(下拉列表)")
    public Result getDepartmentList() {
        return departmentService.getDepartmentList();
    }

    @DeleteMapping
    @ApiOperation("批量删除部门")
    public Result deleteBathDepartment(@RequestBody List<Integer> ids) {
        return departmentService.deleteBathDepartment(ids);
    }

    @PostMapping
    @ApiOperation(value ="新增部门",notes = "需要部门名称、简介字段")
    public Result addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @PutMapping
    @ApiOperation(value = "修改部门信息",notes = "需要id、部门名称、简介字段")
    public Result updateDepartment(@RequestBody Department department) {
        return departmentService.updateDepartment(department);
    }

}
