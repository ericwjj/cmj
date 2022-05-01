package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.DepartmentEmployeeMapper;
import com.soft2146.crm.api.mapper.EmployeeMapper;
import com.soft2146.crm.api.mapper.PositionMapper;
import com.soft2146.crm.api.model.entity.Position;
import com.soft2146.crm.api.model.vo.PositionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-09 09:25
 **/
@Service
public class PositionService extends ServiceImpl<PositionMapper, Position> {

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private DepartmentEmployeeMapper departmentEmployeeMapper;

    public Result getPositionListByPage(Integer pageNo, Integer pageSize) {
        Page<PositionVo> page = new Page<>(pageNo, pageSize);
        IPage<PositionVo> positionVoIPage = baseMapper.getPositionListByPage(page);
        return Result.success(positionVoIPage);
    }

    public Result deleteBathPosition(List<Integer> ids) {
        // 删除职位
        baseMapper.deleteBathPosition(ids);
        // 将员工信息中的职位置0
        employeeMapper.updateBathPosition(ids);
        // 删除员工和部门关联关系
        // 查询出部门 1.职位id查询部门id
        List<Integer> depIds = baseMapper.selectBathPosition(ids);
        departmentEmployeeMapper.deleteBathDepEmp(depIds);
        return Result.success();
    }


    public Result updatePosition(Position position) {
        Position position1 = lambdaQuery().eq(Position::getId, position.getId())
                .eq(Position::getDeleteFlag, false).one();
        if (position1 != null) {
            position.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            saveOrUpdate(position);
            return Result.success("update success");
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    public Result addPosition(Position position) {
        Position position1 = lambdaQuery().eq(Position::getPositionName, position.getPositionName())
                .eq(Position::getDeleteFlag, false).one();
        if (position1 != null) {
            return Result.failure(ResultCode.NAME_IS_FOUND);
        } else {
            position.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
            position.setDeleteFlag(false);
            saveOrUpdate(position);
            return Result.success("add success");
        }

    }

    public Result getPositionListByDepId(Integer depId) {
        List<Position> positionList = lambdaQuery().eq(Position::getDepId, depId)
                .eq(Position::getDeleteFlag, false).list();
        return Result.success(positionList);
    }
}
