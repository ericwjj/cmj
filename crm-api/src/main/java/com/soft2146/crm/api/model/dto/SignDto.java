package com.soft2146.crm.api.model.dto;

import lombok.*;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-11-26 08:49
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignDto {
    private String phone;
    private String password;
    private Integer roleId;
}
