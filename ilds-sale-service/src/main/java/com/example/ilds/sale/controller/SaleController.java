package com.example.ilds.sale.controller;

import com.example.ilds.sale.model.Sale;
import com.example.ilds.sale.service.SaleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
@PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN' ,'ROLE_SALE')")
public class SaleController {
    @Resource
    private SaleService saleService;

    @PostMapping("")
    public Sale save(@RequestBody Sale sale) {
        return saleService.save(sale);
    }

    @GetMapping("")
    public List<Sale> findAll() {
        return saleService.findAll();
    }

    @GetMapping("/search/{name}")
    public List<Sale> search(@PathVariable String name) {
        return saleService.searchByCompany(name);
    }

}
