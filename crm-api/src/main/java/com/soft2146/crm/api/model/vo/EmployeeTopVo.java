package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-21 11:47
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTopVo {
    private String employeeName;
    private Double totalMoney;
}
