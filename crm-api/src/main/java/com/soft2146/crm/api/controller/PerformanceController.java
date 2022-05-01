package com.soft2146.crm.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.entity.Performance;
import com.soft2146.crm.api.service.PerformanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-16 08:32
 **/
@RestController
@RequestMapping(value = "/per")
@Api(value = "PerformanceController", tags = {"业绩模块"})
public class PerformanceController {
    @Resource
    private PerformanceService performanceService;


    @GetMapping()
    @ApiOperation("分页查询所有业绩信息")
    public Result getPerformanceListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        return Result.success(performanceService.lambdaQuery().eq(Performance::getDeleteFlag, false)
                .orderByAsc(Performance::getId).page(page));
    }

    @PutMapping
    @ApiOperation("修改业绩信息信息")
    public Result updatePerformance(@RequestBody Performance performance) {
        return performanceService.updatePerformance(performance);
    }

    @DeleteMapping
    @ApiOperation("批量删除员工业绩信息")
    public Result deleteBathPerformance(@RequestBody List<Integer> ids) {
        return performanceService.deleteBathPerformance(ids);
    }

    @GetMapping("/top10")
    @ApiOperation("查询前十业绩的销售员")
    public Result getTop10List() {
        return performanceService.getTop10List();
    }

}
