package com.soft2146.crm.api.controller;

import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.entity.Position;
import com.soft2146.crm.api.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-09 09:25
 **/
@RestController
@RequestMapping(value = "/position")
@Api(value = "PositionController", tags = {"职位模块"})
public class PositionController {
    @Resource
    private PositionService positionService;


    @GetMapping
    @ApiOperation("分页查询所有职位")
    public Result getPositionListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return positionService.getPositionListByPage(pageNo, pageSize);
    }

    @DeleteMapping
    @ApiOperation("批量删除职位")
    public Result deleteBathPosition(@RequestBody List<Integer> ids) {
        return positionService.deleteBathPosition(ids);
    }

    @PutMapping
    @ApiOperation("修改职位信息")
    public Result updatePosition(@RequestBody Position position) {
        return positionService.updatePosition(position);
    }


    @PostMapping
    @ApiOperation("新增职位")
    public Result addPosition(@RequestBody Position position) {
        return positionService.addPosition(position);
    }

    @GetMapping("/dep")
    @ApiOperation(value = "根据部门id查询该部门下的职位", notes = "部门id")
    public Result getPositionListByDepId(@RequestParam(name = "depId", defaultValue = "") Integer depId) {
        return positionService.getPositionListByDepId(depId);
    }
}
