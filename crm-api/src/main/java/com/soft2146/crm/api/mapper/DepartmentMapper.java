package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft2146.crm.api.model.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    void deleteBathDepartment(@Param("ids") List<Integer> ids);

}
