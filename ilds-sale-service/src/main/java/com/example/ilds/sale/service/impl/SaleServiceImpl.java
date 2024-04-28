package com.example.ilds.sale.service.impl;

import com.example.common.util.DataTimeUtil;
import com.example.ilds.sale.model.Sale;
import com.example.ilds.sale.repository.SaleRepository;
import com.example.ilds.sale.service.SaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleRepository saleRepository;

    @Override
    public Sale save(Sale sale) {
        sale.setCreateAt(DataTimeUtil.getNowTimeString());
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> searchByCompany(String name) {
        return saleRepository.findAllByCompanyLike(name);
    }

}
