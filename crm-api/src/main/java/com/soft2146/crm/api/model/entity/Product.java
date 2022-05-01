package com.soft2146.crm.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description: 产品
 * @Author: Tao
 * @Date: 2021-12-08 16:18
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 产品名称
     */
    @Column(nullable = false, unique = true)
    private String productName;

    /**
     * 产品类型
     */
    @Column(nullable = false, unique = true)
    private String productType;


    /**
     * 价格
     */
    @Column(nullable = false, unique = true)
    private Double price;

    /**
     * 库存数量
     */
    @Column(nullable = false, unique = true)
    private Integer repertory;


    /**
     * 封面
     */
    @Column(nullable = false, unique = true)
    private String img;

    /**
     * 修改时间
     */
    @Column(nullable = false)
    @JsonIgnore
    private Timestamp updateTime;

    /**
     * 是否删除（0 未删除 ，1 逻辑删除）
     */

    @Column(length = 4, nullable = false)
    @JsonIgnore
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    @JsonIgnore
    @Column(nullable = false, updatable = false)
    private Timestamp createTime;

}
