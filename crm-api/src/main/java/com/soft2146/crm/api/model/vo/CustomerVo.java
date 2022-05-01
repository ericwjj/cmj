package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-16 10:51
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVo {
    private Integer id;
    private String cuName;
    private String phone;
    private String address;
    private Integer credit;
    private String token;
}
