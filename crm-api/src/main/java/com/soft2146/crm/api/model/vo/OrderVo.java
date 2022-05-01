package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-14 08:49
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo {
    private Integer id;
    private Integer customerId;
    private Integer productId;
    private Integer number;
    private Integer employeeId;
    private String cuName;
    private String productName;
    private String employeeName;
    private LocalDateTime createTime;
    private Double price;
    private String img;
}
