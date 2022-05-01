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
 * @Description: 员工
 * @Author: Tao
 * @Date: 2021-12-08 13:25
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工名称
     */
    @Column(nullable = false, length = 32, unique = true)
    private String employeeName;

    /**
     * 性别
     */
    @Column(nullable = false, length = 4, unique = true)
    private String sex;

    /**
     * 年龄
     */
    @Column(nullable = false, unique = true)
    private Integer age;

    /**
     * 工资
     */
    @Column(nullable = false, unique = true)
    private Double wage;

    /**
     * 员工类型
     */
    @Column(nullable = false, length = 32, unique = true)
    private String employeeType;

    /**
     * 职位id
     */
    @Column(nullable = false, unique = true)
    private Integer positionId;

    /**
     * 手机号码
     */
    @Column(nullable = false, length = 11, unique = true)
    private String phone;


    /**
     * 密码
     */
//    @JsonIgnore
    @Column(nullable = false, length = 32, unique = true)
    private String password;

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
