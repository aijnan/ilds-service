package com.example.ilds.api.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仓库
 */
@Data
@NoArgsConstructor
public class WarehouseVO {
    private String id;
    //仓库名称
    private String name;
    //仓库负责人
    private String principle;
    private String createAt;
    private String address;
}
