package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.mapper.DepartmentEmployeeMapper;
import com.soft2146.crm.api.mapper.DepartmentMapper;
import com.soft2146.crm.api.mapper.PositionMapper;
import com.soft2146.crm.api.model.entity.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 14:34
 **/
@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, Department> {
    @Resource
    private UserLogService userLogService;
    @Resource
    private PositionMapper positionMapper;
    @Resource
    private PositionService positionService;
    @Resource
    private DepartmentEmployeeMapper departmentEmployeeMapper;

    public Result getDepartmentList() {
        List<Department> departments = lambdaQuery().eq(Department::getDeleteFlag, false).list();
        return Result.success(departments);
    }

    public Result deleteBathDepartment(List<Integer> ids) {
        // 删除部门信息
        baseMapper.deleteBathDepartment(ids);
        // 1.查询职位
        List<Integer> positionIds = positionMapper.selectBathPositionId(ids);
        // 2.删除职位
        positionService.deleteBathPosition(positionIds);
//        // 删除部门和员工关联信息
//        departmentEmployeeMapper.deleteBathDepEmp(ids);
        return Result.success();
    }

    public Result addDepartment(Department department) {
        department.setDeleteFlag(false);
        department.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        saveOrUpdate(department);
        return Result.success("add success");
    }

    public Result updateDepartment(Department department) {
        Department department1 = lambdaQuery().eq(Department::getId, department.getId())
                .eq(Department::getDeleteFlag, false).one();
        department1.setDepName(department.getDepName());
        department1.setDescription(department.getDescription());
        department1.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        saveOrUpdate(department1);
        return Result.success("add success");
    }
}
