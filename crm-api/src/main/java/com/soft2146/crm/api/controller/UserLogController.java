package com.soft2146.crm.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.entity.UserLog;
import com.soft2146.crm.api.service.UserLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-10 08:57
 **/
@RestController
@RequestMapping(value = "/log")
@Api(value = "UserLogController", tags = {"日志模块"})
public class UserLogController {
    @Resource
    private UserLogService userLogService;

    @GetMapping()
    @ApiOperation("分页查询所有日志")
    public Result getLogByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        return Result.success(userLogService.lambdaQuery().eq(UserLog::getDeleteFlag, false)
                .orderByAsc(UserLog::getId).page(page));
    }

    @DeleteMapping
    @ApiOperation("批量删除日志")
    public Result deleteBathLog(@RequestBody List<Integer> ids) {
        return userLogService.deleteBathLog(ids);
    }
}
