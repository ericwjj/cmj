package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-09 10:31
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanEmpVo {
    private Integer id;
    private Integer employeeId;
    private Integer customerId;
    private Double planProfits;
    private Timestamp planTime;
    private Boolean ifReach;
    private String implementation;

    private String cuName;
    private String phone;

    private String employeeName;
}
