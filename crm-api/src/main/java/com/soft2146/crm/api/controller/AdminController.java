package com.soft2146.crm.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.dto.SignDto;
import com.soft2146.crm.api.model.entity.Admin;
import com.soft2146.crm.api.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 08:31
 **/
@RestController
@RequestMapping(value = "/admin")
@Api(value = "AdminController", tags = {"管理员模块"})
public class AdminController {
    @Resource
    private AdminService adminService;

    @GetMapping()
    @ApiOperation("分页查询所有管理员用户")
    public Result getSysUserList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        return Result.success(adminService.lambdaQuery().eq(Admin::getDeleteFlag,false)
                .orderByAsc(Admin::getId).page(page));
//        return sysUserService.getUserByPage(pageNo,pageSize);
    }

    @ApiOperation(value = "帐密登录(管理员与员工共用接口)",notes = "role为1管理员角色 2普通员工")
    @PostMapping(value = "/login")
    public Result login(@RequestBody SignDto signDto){
        return adminService.login(signDto);
    }

}
