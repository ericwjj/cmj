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
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-16 08:28
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工id
     */
    @Column(nullable = false, unique = true)
    private Integer employeeId;

    /**
     * 员工姓名
     */
    @Column(nullable = false, length = 32, unique = true)
    private String employeeName;


    /**
     * 订单数量
     */
    @Column(nullable = false, unique = true)
    private Integer orderNumber;

    /**
     * 当月总订单金额
     */
    @Column(nullable = false, unique = true)
    private Double totalMoney;

    /**
     * 年月
     */
    @Column(nullable = false, unique = true)
    private String nowTime;

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
