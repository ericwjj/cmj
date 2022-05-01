package com.soft2146.crm.api.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft2146.crm.api.common.Result;
import com.soft2146.crm.api.model.entity.Product;
import com.soft2146.crm.api.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 16:24
 **/
@RestController
@RequestMapping(value = "/product")
@Api(value = "ProductController", tags = {"产品模块"})
public class ProductController {
    @Resource
    private ProductService productService;

    @GetMapping()
    @ApiOperation("分页查询所有产品")
    public Result getProductListByPage(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        return Result.success(productService.lambdaQuery().eq(Product::getDeleteFlag, false)
                .orderByAsc(Product::getId).page(page));
    }

    @DeleteMapping
    @ApiOperation("批量删除产品")
    public Result deleteBathProduct(@RequestBody List<Integer> ids) {
        return productService.deleteBathProduct(ids);
    }

    @PostMapping
    @ApiOperation("新增产品")
    public Result addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping
    @ApiOperation("修改员工信息")
    public Result updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @GetMapping("/pull")
    @ApiOperation("查询产品列表(下拉列表)")
    public Result getProductList() {
        return productService.getProductList();
    }

    @GetMapping("/top10")
    @ApiOperation("查询前十销量产品")
    public Result getTop10List() {
        return productService.getTop10List();
    }

    @GetMapping("/details")
    @ApiOperation("根据产品id获取详情信息")
    public Result getProductById(@RequestParam(name = "proId", defaultValue = "-1") Integer proId) {
        return productService.getProductById(proId);
    }
}
