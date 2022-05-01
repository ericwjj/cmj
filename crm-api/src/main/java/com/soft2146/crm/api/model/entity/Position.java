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
 * @Description: 职位
 * @Author: Tao
 * @Date: 2021-12-09 08:50
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    /**
     * 部门id
     */
    @Column(nullable = false, unique = true)
    private Integer depId;

    /**
     * 职位名称
     */
    @Column(nullable = false, length = 32, unique = true)
    private String positionName;

    /**
     * 工作内容
     */
    @Column(nullable = false, length = 32, unique = true)
    private String workContent;

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
