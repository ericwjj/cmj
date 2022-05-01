package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft2146.crm.api.model.entity.Customer;
import com.soft2146.crm.api.model.vo.CuAddVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface CustomerMapper extends BaseMapper<Customer> {
    List<CuAddVo> getThreeMonthNum();

    void deleteBathCustomer(@Param("ids") List<Integer> ids);

}
