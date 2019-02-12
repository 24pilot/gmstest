package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Basic DTO for @{@link com.example.demo.model.CurrencyExchange}
 */
public class CurrencyExchangeDTO {

    private Long id;
    private String base;
    private Date date;
    private Map<String, BigDecimal> rates;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }
}
