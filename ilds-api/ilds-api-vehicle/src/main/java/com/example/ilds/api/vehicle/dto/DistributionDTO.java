package com.example.ilds.api.vehicle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 配送
 */
@Data
@NoArgsConstructor
public class DistributionDTO {
    private String id;

    //司机id
    private String did;

    //车辆id
    private String vid;

    //司机
    private String driver;

    //车牌号
    private String number;

    //客户电话
    private String phone;

    //客户地址
    private String address;

    //加急处理
    private boolean urgent;

    private String care;
    //操作时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime time;

    private Integer status;

    private String departureAddress;
}
