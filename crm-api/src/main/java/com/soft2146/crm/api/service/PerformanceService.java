package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.PerformanceMapper;
import com.soft2146.crm.api.model.entity.Performance;
import com.soft2146.crm.api.model.vo.EmployeeTopVo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-16 08:32
 **/
@Service
public class PerformanceService extends ServiceImpl<PerformanceMapper, Performance> {

    public Result updatePerformance(Performance performance) {
        Performance performance1 = lambdaQuery().eq(Performance::getId, performance.getId())
                .eq(Performance::getDeleteFlag, false).one();
        if (performance1 != null) {
            performance1.setEmployeeId(performance.getEmployeeId());
            performance1.setEmployeeName(performance.getEmployeeName());
            performance1.setOrderNumber(performance.getOrderNumber());
            performance1.setTotalMoney(performance.getTotalMoney());
            performance1.setNowTime(performance.getNowTime());
            performance1.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            saveOrUpdate(performance1);
            return Result.success("update success");
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    public Result deleteBathPerformance(List<Integer> ids) {
        baseMapper.deleteBathPerformance(ids);
        return Result.success();
    }

    public Result getTop10List() {
//        String orderTime = String.valueOf(LocalDateTime.now());
//        String yearMonth = orderTime.substring(0, 4) + orderTime.substring(5, 7);
        List<EmployeeTopVo> employeeTopVos =baseMapper.getTop10List();
        return Result.success(employeeTopVos);
    }
}
