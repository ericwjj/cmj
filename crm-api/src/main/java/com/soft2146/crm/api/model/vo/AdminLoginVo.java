package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-11-26 09:34
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginVo {

    private Integer id;

    private String adminName;

    private String phone;

    private String avatar;

    private String token;


}
