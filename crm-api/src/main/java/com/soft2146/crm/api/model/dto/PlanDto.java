package com.soft2146.crm.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-09 08:21
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {
    private Integer id;
    private Integer employeeId;
    private Integer customerId;
    private Double planProfits;
    private String planTime;
}
