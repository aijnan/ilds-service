package com.example.ilds.api.base.feign;

import com.example.ilds.api.base.vo.WarehouseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "wms-base",contextId ="warehouse", url="http://127.0.0.1:8081")
public interface WarehouseFeignClient {

	@GetMapping(value = "/feign/warehouse/find")
	WarehouseVO findById(String id);
}
