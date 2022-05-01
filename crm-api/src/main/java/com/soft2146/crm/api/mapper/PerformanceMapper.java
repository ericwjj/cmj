package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft2146.crm.api.model.entity.Performance;
import com.soft2146.crm.api.model.vo.EmployeeTopVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-16 08:31
 **/
public interface PerformanceMapper extends BaseMapper<Performance> {
    void deleteBathPerformance(@Param("ids") List<Integer> ids);

    Performance selectPer(@Param("employeeId") int employeeId,
                          @Param("nowTime") String nowTime);


    List<EmployeeTopVo> getTop10List();

}
