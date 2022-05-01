package com.soft2146.crm.api.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.AdminMapper;
import com.soft2146.crm.api.model.dto.SignDto;
import com.soft2146.crm.api.model.entity.Admin;
import com.soft2146.crm.api.model.entity.Employee;
import com.soft2146.crm.api.model.vo.AdminLoginVo;
import com.soft2146.crm.api.model.vo.EmployeeLoginVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 08:28
 **/
@Service
public class AdminService extends ServiceImpl<AdminMapper, Admin> {
    @Resource
    private EmployeeService employeeService;

    /**
     * 账号密码登录
     * @param signDto
     * @return
     */
    public Result login(SignDto signDto) {
        String phone = signDto.getPhone();
        String password = signDto.getPassword();
        if (signDto.getRoleId() == 1) {
            Admin admin = lambdaQuery().eq(Admin::getPhone, phone)
                    .eq(Admin::getDeleteFlag, false).one();
            if (admin != null) {
                if (DigestUtils.md5Hex(password).equals(admin.getPassword())) {
                    if (admin.getStatus()) {
                        StpUtil.login(admin.getId());
                        String token = StpUtil.getTokenValue();
                        AdminLoginVo admin1 = AdminLoginVo.builder()
                                .id(admin.getId())
                                .adminName(admin.getAdminName())
                                .phone(admin.getPhone())
                                .avatar(admin.getAvatar())
                                .token(token)
                                .build();
                        return Result.success(admin1);
                    }
                    return Result.failure(ResultCode.USER_ACCOUNT_FORBIDDEN);
                }
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
            return Result.failure(ResultCode.USER_ACCOUNT_ERROR);
        } else if (signDto.getRoleId() == 2) {
            Employee employee = employeeService.lambdaQuery().eq(Employee::getPhone, phone)
                    .eq(Employee::getDeleteFlag, false).one();
            if (employee != null) {
                if (DigestUtils.md5Hex(password).equals(employee.getPassword())) {
                    StpUtil.login(employee.getId());
                    String token = StpUtil.getTokenValue();
                    EmployeeLoginVo employeeLoginVo = EmployeeLoginVo.builder()
                            .id(employee.getId())
                            .employeeName(employee.getEmployeeName())
                            .sex(employee.getSex())
                            .age(employee.getAge())
                            .wage(employee.getWage())
                            .employeeType(employee.getEmployeeType())
                            .token(token)
                            .build();
                    return Result.success(employeeLoginVo);
                }
                return Result.failure(ResultCode.USER_PASSWORD_ERROR);
            }
            return Result.failure(ResultCode.USER_ACCOUNT_ERROR);
        } else {
            return Result.failure(ResultCode.ROLE_NOT_FOUND);
        }

    }
}
