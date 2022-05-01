package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.FeedbackMapper;
import com.soft2146.crm.api.model.entity.Feedback;
import com.soft2146.crm.api.model.vo.FeedbackVo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-11 18:29
 **/
@Service
public class FeedbackService extends ServiceImpl<FeedbackMapper, Feedback> {
    public Result getFeedbackListByPage(Integer pageNo, Integer pageSize) {
        Page<FeedbackVo> page = new Page<>(pageNo, pageSize);
        IPage<FeedbackVo> feedbackVoIPage = baseMapper.getFeedbackListByPage(page);
        return Result.success(feedbackVoIPage);
    }

    public Result deleteBathFeedback(List<Integer> ids) {
        baseMapper.deleteBathFeedback(ids);
        return Result.success();
    }

    public Result addFeedback(Feedback feedback) {
        feedback.setSolveResult("未解决");
        feedback.setStatus(0);
        feedback.setDeleteFlag(false);
        feedback.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        saveOrUpdate(feedback);
        return Result.success("add success");
    }

    public Result updateFeedback(Feedback feedback) {
        Feedback feedback1 = lambdaQuery().eq(Feedback::getId,feedback.getId())
                .eq(Feedback::getDeleteFlag, false).one();
        if (feedback1 != null) {
            feedback1.setSolveResult(feedback.getSolveResult());
            feedback1.setStatus(feedback.getStatus());
            feedback1.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            saveOrUpdate(feedback1);
            return Result.success("修改成功");
        }
        return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
    }
}
