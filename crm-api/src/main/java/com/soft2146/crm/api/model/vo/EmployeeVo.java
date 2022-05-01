package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-11 17:15
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVo {
    private Integer id;
    private String employeeName;
    private String sex;
    private Integer age;
    private Double wage;
    private String phone;

    private Integer depId;
    private String depName;

    private Integer positionId;
    private String positionName;
}
