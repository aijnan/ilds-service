package com.example.sale.service.impl;

import com.example.common.util.DataTimeUtil;
import com.example.sale.model.Sale;
import com.example.sale.repository.SaleRepository;
import com.example.sale.service.SaleService;
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
