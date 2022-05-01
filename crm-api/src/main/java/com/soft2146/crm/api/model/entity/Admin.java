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
 * @Description: 管理员
 * @Author: Tao
 * @Date: 2021-12-08 08:24
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 管理员名称
     */
    @Column(nullable = false, length = 32, unique = true)
    private String adminName;

    /**
     * 密码
     */
    @Column(nullable = false, length = 32, unique = true)
    private String password;

    /**
     * 手机号码
     */
    @Column(nullable = false, length = 11, unique = true)
    private String phone;

    /**
     * 手机号码
     */
    @Column(nullable = false, unique = true)
    private String avatar;

    /**
     * 账户状态 0禁用 1启用
     */
    @Column(length = 4, nullable = false)
    private Boolean status;


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
