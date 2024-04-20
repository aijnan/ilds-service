package com.example.wms.api.base.feign;

import com.example.wms.api.base.vo.WarehouseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "wms-base",contextId ="warehouse")
public interface WarehouseFeignClient {

	@GetMapping(value = "/feign/warehouse/find")
	WarehouseVO findById(String id);
}
