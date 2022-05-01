package com.soft2146.crm.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soft2146.crm.api.model.entity.Product;
import com.soft2146.crm.api.model.vo.ProductTopVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Tao_Dell
 */
public interface ProductMapper extends BaseMapper<Product> {
    void deleteBathProduct(@Param("ids") List<Integer> ids);

    List<ProductTopVo> getTop10List();
}
