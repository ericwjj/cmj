package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.model.entity.Feedback;
import com.soft2146.crm.api.model.vo.FeedbackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {
    IPage<FeedbackVo> getFeedbackListByPage(@Param("page") Page page);

    void deleteBathFeedback(@Param("ids") List<Integer> ids);

}
