package com.example.wms.api.vehicle.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 车辆
 */
@Data
@NoArgsConstructor
public class VehicleVO {
    private String id;

    //车牌号
    private String number;

    //车辆类型
    private String type;

    //是否正在运输
    private boolean driving;

    private String createAt;

    private String loadCapacity;

    private double lat;

    private double lng;

    private String speed;

    private  String driver;

    private String address;
}
