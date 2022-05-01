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
 * @Description: 客户
 * @Author: Tao
 * @Date: 2021-12-09 10:32
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 客户名称
     */
    @Column(nullable = false, length = 32, unique = true)
    private String cuName;

    /**
     * 客户手机
     */
    @Column(nullable = false, length = 11, unique = true)
    private String phone;

    /**
     * 客户密码
     */
    @Column(nullable = false, length = 32, unique = true)
    private String password;

    /**
     * 客户地址
     */
    @Column(nullable = false, length = 32, unique = true)
    private String address;

    /**
     * 信用度
     */
    @Column(nullable = false, unique = true)
    private Integer credit;


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
    @Column(nullable = false, updatable = false)
    @JsonIgnore
    private Timestamp createTime;
}
