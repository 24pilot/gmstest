package com.example.demo;

import com.example.demo.dto.CurrencyExchangeDTO;
import com.example.demo.exception.ResourceValidationException;
import com.example.demo.model.CurrencyExchange;
import com.example.demo.service.CurrencyExchangeService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyExchangeServiceTest {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void whenSaveExpectSavedEntity() throws ResourceValidationException {
        CurrencyExchange c = new CurrencyExchange();
        c.setBase("EUR");
        c.setDate(new Date());
        currencyExchangeService.save(c);
        List<CurrencyExchangeDTO> saved = currencyExchangeService.findLastTen();
        assertEquals(1, saved.size());
        CurrencyExchangeDTO first = saved.get(0);
        assertEquals("EUR", first.getBase());
    }

    @Test
    public void whenSaveWithoutDateExpectedResourceValidationException() throws ResourceValidationException {
        CurrencyExchange c = new CurrencyExchange();
        c.setDate(new Date());
        exception.expect(ResourceValidationException.class);
        exception.expectMessage("CurrencyExchange base is null");
        currencyExchangeService.save(c);
    }

    @Test
    public void whenSaveWithoutBaseExpectedResourceValidationException() throws ResourceValidationException {
        CurrencyExchange c = new CurrencyExchange();
        c.setBase("EUR");
        exception.expect(ResourceValidationException.class);
        exception.expectMessage("CurrencyExchange date is null");
        currencyExchangeService.save(c);
    }

    @Test
    public void whenGetLastTenRecordsExpectLastTenOrdered() throws ResourceValidationException {
        CurrencyExchange c = new CurrencyExchange();
        c.setId(1L);
        c.setBase("EUR");
        c.setDate(new Date());
        currencyExchangeService.save(c);

        CurrencyExchange c1 = new CurrencyExchange();
        c1.setId(2L);
        c1.setBase("USD");
        c1.setDate(new Date());
        currencyExchangeService.save(c1);
        List<CurrencyExchangeDTO> saved = currencyExchangeService.findLastTen();
        assertEquals(2, saved.size());
        assertEquals(saved.get(0).getId().longValue(), 2);
    }
}
