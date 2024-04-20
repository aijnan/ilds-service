package com.example.sale.service;

import com.example.sale.model.Sale;
import java.util.List;

public interface SaleService {

    Sale save(Sale sale);

    List<Sale> findAll();

    List<Sale> searchByCompany(String name);

}
