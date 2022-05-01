package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-18 22:21
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrdersVo {
    private Integer id;
    private Integer customerId;
    private Integer productId;
    private Integer number;
    private Integer employeeId;
    private String productName;
    private String img;
    private Double price;
    private LocalDateTime createTime;
    private String employeeName;
}
