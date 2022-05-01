package com.soft2146.crm.api.controller;

import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.entity.Feedback;
import com.soft2146.crm.api.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-11 18:29
 **/
@RestController
@RequestMapping(value = "/feedback")
@Api(value = "FeedbackController", tags = {"反馈模块"})
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    @GetMapping
    @ApiOperation("分页查询所有反馈")
    public Result getFeedbackListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return feedbackService.getFeedbackListByPage(pageNo, pageSize);
    }


    @DeleteMapping
    @ApiOperation("批量删除反馈/客户撤销反馈")
    public Result deleteBathFeedback(@RequestBody List<Integer> ids) {
        return feedbackService.deleteBathFeedback(ids);
    }


    @PostMapping
    @ApiOperation(value = "新增反馈", notes = "需要字段：反馈内容,客户id,产品id,处理人id")
    public Result addFeedback(@RequestBody Feedback feedback) {
        return feedbackService.addFeedback(feedback);
    }

    @PutMapping
    @ApiOperation(value = "修改员工信息", notes = "需要字段：Id,解决情况,状态")
    public Result updateFeedback(@RequestBody Feedback feedback) {
        return feedbackService.updateFeedback(feedback);
    }
}

