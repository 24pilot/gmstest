package com.example.demo.scheduller;

import com.example.demo.connection.HttpConnection;
import com.example.demo.exception.ResourceValidationException;
import com.example.demo.model.CurrencyExchange;
import com.example.demo.service.CurrencyExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Scheduler which every 5 seconds retrieves @{@link CurrencyExchange} object and save
 * it to database
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    public ScheduledTasks(CurrencyExchangeService currencyExchangeService) {
        this.currencyExchangeService = currencyExchangeService;
    }

    //fixedRate in milliseconds
    @Scheduled(fixedRate = 5000)
    public void reportCurrentData() {
        try {
            CurrencyExchange currencyExchange = HttpConnection.getCurrencyExchange("https://api.exchangeratesapi.io/latest");
            log.info(currencyExchange.toString());
            //set Base currency string before base currency
            currencyExchange.setBase("Base currency: " + currencyExchange.getBase());
            //get currency rates and increase it on 0.1
            currencyExchange.getRates().forEach((k, v) -> currencyExchange.getRates().put(k, v.add(new BigDecimal("0.1"))));
            //saving currency exchange to database
            currencyExchangeService.save(currencyExchange);
        } catch (ResourceValidationException rce) {
            log.error("ScheduledTasks reportCurrentData ResourceValidationException", rce);
        } catch (Exception e) {
            log.error("ScheduledTasks reportCurrentData exception occurred", e);
        }
    }
}
