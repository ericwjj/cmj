package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.EmployeeMapper;
import com.soft2146.crm.api.model.dto.PlanDto;
import com.soft2146.crm.api.model.entity.DepartmentEmployee;
import com.soft2146.crm.api.model.entity.Employee;
import com.soft2146.crm.api.model.entity.Plan;
import com.soft2146.crm.api.model.vo.CuAddVo;
import com.soft2146.crm.api.model.vo.EmployeeVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 13:30
 **/
@Service
public class EmployeeService extends ServiceImpl<EmployeeMapper, Employee> {
    @Resource
    private DepartmentEmployeeService departmentEmployeeService;
    @Resource
    private PlanService planService;


    public Result getEmployeeListByPage(Integer pageNo, Integer pageSize) {
        Page<EmployeeVo> page = new Page<>(pageNo, pageSize);
        IPage<EmployeeVo> employeeVoIPage = baseMapper.getEmployeeListByPage(page);
        return Result.success(employeeVoIPage);
    }


    public Result deleteBathEmployee(List<Integer> ids) {
        baseMapper.deleteBathEmployee(ids);
        return Result.success();
    }

    public Result addEmployee(Employee employee) {
//        Employee saveEmp = Employee.builder()
//                .employeeName(employee.getEmployeeName())
//                .sex(employee.getSex())
//                .age(employee.getAge())
//                .wage(employee.getWage())
//                .employeeType(employee.getEmployeeType())
//                .phone(employee.getPhone())
//                .password(DigestUtils.md5Hex(employee.getPassword()))
//                .createTime(Timestamp.valueOf(LocalDateTime.now()))
//                .deleteFlag(false)
//                .updateTime()
//                .build();
        employee.setDeleteFlag(false);
        employee.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        employee.setPassword(DigestUtils.md5Hex(employee.getPassword()));
        saveOrUpdate(employee);
        return Result.success("add success");
    }

    public Result updateEmployee(Employee employee) {
        Employee employee1 = lambdaQuery().eq(Employee::getId, employee.getId())
                .eq(Employee::getDeleteFlag, false).one();
        if (employee1 != null) {
            employee1.setEmployeeName(employee.getEmployeeName());
            employee1.setSex(employee.getSex());
            employee1.setAge(employee.getAge());
            employee1.setWage(employee.getWage());
            employee1.setEmployeeType(employee.getEmployeeType());
            employee1.setPositionId(employee.getPositionId());
            employee1.setPhone(employee.getPhone());
            employee1.setPassword(DigestUtils.md5Hex(employee.getPassword()));
            employee1.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            saveOrUpdate(employee1);
            return Result.success("修改成功");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }


    public void getCarSpeed(int peopleSpeed) {
        String carSpeed = "车速：" + peopleSpeed * 10;
        System.out.println(carSpeed);
    }


    public Result insertEmpToDep(DepartmentEmployee departmentEmployee) {
        DepartmentEmployee departmentEmployee1 = departmentEmployeeService.lambdaQuery()
                .eq(DepartmentEmployee::getEmployeeId, departmentEmployee.getEmployeeId())
                .eq(DepartmentEmployee::getDeleteFlag, false).one();
        if (departmentEmployee1 != null) {
            return Result.failure(ResultCode.USER_DEP_ERROR);
        } else {
            departmentEmployee.setDeleteFlag(false);
            departmentEmployee.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            departmentEmployeeService.saveOrUpdate(departmentEmployee);
            return Result.success("add success");
        }
    }

    public Result insertPlanToDep(PlanDto planDto) {
        Plan plan1 = planService.lambdaQuery().eq(Plan::getEmployeeId, planDto.getEmployeeId())
                .eq(Plan::getCustomerId, planDto.getCustomerId())
                .eq(Plan::getDeleteFlag, false).one();
        if (plan1 != null) {
            return Result.failure(ResultCode.USER_PLAN_FOUND);
        } else {
            Plan plan = Plan.builder()
                    .employeeId(planDto.getEmployeeId())
                    .customerId(planDto.getCustomerId())
                    .planProfits(planDto.getPlanProfits())
                    .planTime(Timestamp.valueOf(planDto.getPlanTime()))
                    .ifReach(false)
                    .implementation("进行中")
                    .deleteFlag(false)
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
            planService.saveOrUpdate(plan);
            return Result.success("add success");
        }
    }

    public Result getEmployeeList() {
        List<Employee> employees = lambdaQuery().eq(Employee::getDeleteFlag, false).list();
        return Result.success(employees);
    }

    public Result getThreeMonthNum() {
        List<CuAddVo> cuAddVos = baseMapper.getThreeMonthNum();
        return Result.success(cuAddVos);
    }


}
