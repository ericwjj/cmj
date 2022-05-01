package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-08 13:39
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeLoginVo {
    private Integer id;
    private String employeeName;
    private String sex;
    private Integer age;
    private Double wage;
    private String employeeType;
    private String phone;
    private String token;
}
