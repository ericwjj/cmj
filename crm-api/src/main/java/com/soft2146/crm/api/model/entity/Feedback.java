package com.soft2146.crm.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Description: 反馈
 * @Author: Tao
 * @Date: 2021-12-11 18:20
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 反馈内容
     */
    @Column(nullable = false, unique = true)
    private String content;

    /**
     * 客户id
     */
    @Column(nullable = false, unique = true)
    private Integer customerId;

    /**
     * 产品id
     */
    @Column(nullable = false, unique = true)
    private Integer productId;


    /**
     * 处理人id
     */
    @Column(nullable = false, unique = true)
    private Integer disposeId;

    /**
     * 解决情况
     */
    @Column(nullable = false, unique = true)
    private String solveResult;

    /**
     * 反馈进度（0提交未处理 1已处理 2已退回）
     */
    @Column(length = 4, nullable = false)
    private Integer status;

    /**
     * 修改时间
     */
    @Column(nullable = false)
    private Timestamp updateTime;

    /**
     * 是否删除（0 未删除 ，1 逻辑删除）
     */
    @Column(length = 4, nullable = false)
    private Boolean deleteFlag;

    /**
     * 创建时间
     */
    @Column(nullable = false, updatable = false)
    private Timestamp createTime;
}
