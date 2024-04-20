package com.example.wms.api.vehicle.feign;

import com.example.wms.api.vehicle.dto.DistributionDTO;
import com.example.wms.api.vehicle.vo.VehicleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "wms-vehicle",contextId ="distribution")
public interface DistributionFeignClient {

	/**
	 *
	 * @param wid 仓库id
	 * @return
	 */
	@GetMapping(value = "/feign/vehicle/get")
	VehicleVO getOptimalVehicle(String wid);

	@PostMapping(value = "/feign/vehicle/save")
	void saveDistribution(DistributionDTO dto) throws Exception;
}
