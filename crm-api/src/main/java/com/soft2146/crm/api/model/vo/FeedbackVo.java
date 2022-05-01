package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-11 18:32
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackVo {
    private Integer id;
    private String content;
    private Integer customerId;
    private Integer productId;
    private Integer disposeId;
    private String solveResult;
    private Integer status;

    private String cuName;
    private String productName;
    private String employeeName;
}
