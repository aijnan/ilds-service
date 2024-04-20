package com.example.ilds.vehicle.feign;

import com.example.common.util.BeanUtil;
import com.example.ilds.api.vehicle.dto.DistributionDTO;
import com.example.ilds.vehicle.model.entity.Distribution;
import com.example.ilds.vehicle.model.entity.Vehicle;
import com.example.ilds.vehicle.service.DistributionService;
import com.example.ilds.api.vehicle.feign.DistributionFeignClient;
import com.example.ilds.api.vehicle.vo.VehicleVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DistributionFeignController implements DistributionFeignClient {
    private final DistributionService distributionService;
    @Override
    public VehicleVO getOptimalVehicle(String wid) {
        Vehicle optimalVehicle = distributionService.getOptimalVehicle(wid);
        return BeanUtil.map(optimalVehicle, VehicleVO.class);
    }

    @Override
    public void saveDistribution(DistributionDTO dto) throws Exception {
        Distribution distribution = BeanUtil.map(dto, Distribution.class);
        distributionService.save(distribution);
    }
}
