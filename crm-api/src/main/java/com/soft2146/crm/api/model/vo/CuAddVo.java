package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-14 11:22
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuAddVo {
    private Integer month;
    private Integer sums;
}
