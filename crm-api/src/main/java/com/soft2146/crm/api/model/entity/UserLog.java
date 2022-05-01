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
 * @Description: 用户日志
 * @Author: Tao
 * @Date: 2021-12-10 08:48
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLog {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 日志类型（1.新增 2.修改 3.删除 4.执行）
     */
    @Column(nullable = false, unique = true)
    private Integer type;


    /**
     * 日志内容
     */
    @Column(nullable = false, unique = true)
    private String logContent;

    /**
     * 操作用户id
     */
    @Column(nullable = false, unique = true)
    private Integer operateId;

    /**
     * 操作用户名称
     */
    @Column(nullable = false, length = 32, unique = true)
    private String operateName;

    /**
     * 角色（1管理员 2员工）
     */
    @Column(nullable = false, unique = true)
    private Integer role;

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
    private Timestamp createTime;
}
