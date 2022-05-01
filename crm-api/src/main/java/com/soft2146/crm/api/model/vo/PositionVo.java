package com.soft2146.crm.api.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Description:
 * @Author: Tao
 * @Date: 2021-12-09 09:32
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PositionVo {
    private Integer id;
    private Integer depId;
    private String positionName;
    private String workContent;
    private Timestamp createTime;

    private String depName;
}
