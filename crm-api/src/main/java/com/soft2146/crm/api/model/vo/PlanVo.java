package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-11 18:04
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanVo {
    private Integer id;
    private Integer employeeId;
    private Integer customerId;
    private Double planProfits;
    private Timestamp planTime;
    private Boolean ifReach;
    private String implementation;
    private String employeeName;
    private String cuName;
}
