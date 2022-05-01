package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft2146.crm.api.model.entity.DepartmentEmployee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface DepartmentEmployeeMapper extends BaseMapper<DepartmentEmployee> {
    void deleteBathDepEmp(@Param("ids") List<Integer> ids);

}
