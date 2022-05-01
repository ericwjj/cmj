package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.mapper.UserLogMapper;
import com.soft2146.crm.api.model.entity.UserLog;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-10 08:56
 **/
@Service
public class UserLogService extends ServiceImpl<UserLogMapper, UserLog> {
    public Result deleteBathLog(List<Integer> ids) {
        baseMapper.deleteBathLog(ids);
        return Result.success();
    }
}
