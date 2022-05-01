package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.model.entity.Employee;
import com.soft2146.crm.api.model.vo.CuAddVo;
import com.soft2146.crm.api.model.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    void deleteBathEmployee(@Param("ids") List<Integer> ids);

    void updateBathPosition(@Param("ids") List<Integer> ids);

    IPage<EmployeeVo> getEmployeeListByPage(@Param("page") Page page);

    List<CuAddVo> getThreeMonthNum();

}
