package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.model.entity.Position;
import com.soft2146.crm.api.model.vo.PositionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-09 09:22
 **/
public interface PositionMapper extends BaseMapper<Position> {
    IPage<PositionVo> getPositionListByPage(@Param("page") Page page);

    void deleteBathPosition(@Param("ids") List<Integer> ids);

    List<Integer> selectBathPosition(@Param("ids") List<Integer> ids);


    List<Integer> selectBathPositionId(@Param("ids") List<Integer> ids);

}
