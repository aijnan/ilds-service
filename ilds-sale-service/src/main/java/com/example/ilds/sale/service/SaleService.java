package com.example.ilds.sale.service;

import com.example.ilds.sale.model.Sale;

import java.util.List;

public interface SaleService {

    Sale save(Sale sale);

    List<Sale> findAll();

    List<Sale> searchByCompany(String name);

}
