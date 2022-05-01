package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.PlanMapper;
import com.soft2146.crm.api.model.dto.PlanDto;
import com.soft2146.crm.api.model.entity.Plan;
import com.soft2146.crm.api.model.vo.PlanEmpVo;
import com.soft2146.crm.api.model.vo.PlanVo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 16:11
 **/
@Service
public class PlanService extends ServiceImpl<PlanMapper, Plan> {

    public Result getPlanListByPage(Integer pageNo, Integer pageSize) {
        Page<PlanVo> page = new Page<>(pageNo, pageSize);
        IPage<PlanVo> planVoIPage = baseMapper.getPlanListByPage(page);
        return Result.success(planVoIPage);
    }

    public Result deleteBathPlan(List<Integer> ids) {
        baseMapper.deleteBathPlan(ids);
        return Result.success();
    }

    public Result updatePlan(PlanDto planDto) {
        Plan plan = lambdaQuery().eq(Plan::getId, planDto.getId())
                .eq(Plan::getDeleteFlag, false).one();
        if (plan != null) {
            plan.setPlanProfits(planDto.getPlanProfits());
            plan.setPlanTime(Timestamp.valueOf(planDto.getPlanTime()));
            plan.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            saveOrUpdate(plan);
            return Result.success("修改成功");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }

    public Result getPlanByEmpId(Integer empId) {
        List<PlanEmpVo> planEmpVos = baseMapper.getPlanByEmpId(empId);
        return Result.success(planEmpVos);
    }
}
