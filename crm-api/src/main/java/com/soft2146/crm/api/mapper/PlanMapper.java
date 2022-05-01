package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.model.entity.Plan;
import com.soft2146.crm.api.model.vo.PlanEmpVo;
import com.soft2146.crm.api.model.vo.PlanVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface PlanMapper extends BaseMapper<Plan> {
    void deleteBathPlan(@Param("ids") List<Integer> ids);

    List<PlanEmpVo> getPlanByEmpId(@Param("empId") Integer empId);

    IPage<PlanVo> getPlanListByPage(@Param("page") Page page);

}
