package com.example.demo.controller;

import com.example.demo.dto.CurrencyExchangeDTO;
import com.example.demo.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for statistic endpoint
 */
@RestController
public class CurrencyExchangeController {

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    @GetMapping("/")
    public List<CurrencyExchangeDTO> getStatistic() {
        return currencyExchangeService.findLastTen();
    }
}
