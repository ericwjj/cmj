package com.soft2146.crm.api.controller;

import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.dto.PlanDto;
import com.soft2146.crm.api.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 16:15
 **/
@RestController
@RequestMapping(value = "/plan")
@Api(value = "PlanController", tags = {"计划模块"})
public class PlanController {
    @Resource
    private PlanService planService;

    @GetMapping()
    @ApiOperation("分页查询所有计划")
    public Result getPlanListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
//        Page page = new Page<>(pageNo, pageSize);
//        return Result.success(planService.lambdaQuery().eq(Plan::getDeleteFlag, false)
//                .orderByAsc(Plan::getId).page(page));
        return planService.getPlanListByPage(pageNo, pageSize);
    }

    @DeleteMapping
    @ApiOperation("批量删除计划")
    public Result deleteBathPlan(@RequestBody List<Integer> ids) {
        return planService.deleteBathPlan(ids);
    }

    @PutMapping
    @ApiOperation(value ="修改计划",notes = "需要id,计划利润,计划时间")
    public Result updatePlan(@RequestBody PlanDto planDto) {
        return planService.updatePlan(planDto);
    }

    @GetMapping("/emp")
    @ApiOperation(value ="查看单个员工存在的计划列表",notes = "需要员工id")
    public Result getPlanByEmpId(@RequestParam(name = "empId", defaultValue = "") Integer empId) {
        return planService.getPlanByEmpId(empId);
    }


}
