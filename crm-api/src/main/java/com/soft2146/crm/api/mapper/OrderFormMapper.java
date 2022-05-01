package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.model.entity.OrderForm;
import com.soft2146.crm.api.model.vo.CustomerOrdersVo;
import com.soft2146.crm.api.model.vo.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface OrderFormMapper extends BaseMapper<OrderForm> {
    IPage<OrderVo> getOrderListByPage(@Param("page") Page page);

    void deleteBathOrder(@Param("ids") List<Integer> ids);

    List<CustomerOrdersVo> selectOrdersByCustomer(@Param("customerId") Integer customerId);
}
