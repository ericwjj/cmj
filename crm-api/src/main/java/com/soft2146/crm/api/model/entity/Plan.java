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
 * @Description: 计划
 * @Author: Tao
 * @Date: 2021-12-08 16:05
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工id
     */
    @Column(nullable = false, unique = true)
    private Integer employeeId;

    /**
     * 客户id
     */
    @Column(nullable = false, unique = true)
    private Integer customerId;

    /**
     * 计划利润
     */
    @Column(nullable = false, unique = true)
    private Double planProfits;

    /**
     * 计划时间
     */
    @Column(nullable = false, updatable = false)
    private Timestamp planTime;

    /**
     * 是否按计划完成 （0 未完成 1已完成）
     */
    @Column(length = 4, nullable = false)
    private Boolean ifReach;

    /**
     * 实施情况
     */
    @Column(nullable = false, unique = true)
    private String implementation;

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
