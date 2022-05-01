package com.soft2146.crm.api.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.common.ResultCode;
import com.soft2146.crm.api.mapper.ProductMapper;
import com.soft2146.crm.api.model.entity.Product;
import com.soft2146.crm.api.model.vo.ProductTopVo;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 16:23
 **/
@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {
    public Result deleteBathProduct(List<Integer> ids) {
        baseMapper.deleteBathProduct(ids);
        return Result.success();
    }

    public Result addProduct(Product product) {
        product.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        product.setDeleteFlag(false);
        saveOrUpdate(product);
        return Result.success("add success");
    }

    public Result updateProduct(Product product) {
        Product product1 = lambdaQuery().eq(Product::getId, product.getId())
                .eq(Product::getDeleteFlag, false).one();
        if (product1 != null) {
            product1.setProductName(product.getProductName());
            product1.setProductType(product.getProductType());
            product1.setPrice(product.getPrice());
            product1.setRepertory(product.getRepertory());
            product1.setImg(product.getImg());
            product1.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
            saveOrUpdate(product1);
            return Result.success("update success");
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    public Result getProductList() {
        List<Product> products = lambdaQuery().eq(Product::getDeleteFlag, false).list();
        return Result.success(products);
    }

    public Result getTop10List() {
        List<ProductTopVo> products =baseMapper.getTop10List();
        for (int i = 0; i < 10; i++) {
            Product product = lambdaQuery().eq(Product::getId,products.get(i).getProductId()).one();
            products.get(i).setProductName(product.getProductName());
        }
        return Result.success(products);
    }


    public Result getProductById(Integer proId) {
        Product product = lambdaQuery().eq(Product::getId, proId).eq(Product::getDeleteFlag, false).one();
        return Result.success(product);
    }
}
