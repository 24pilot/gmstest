package com.example.demo.service;

import com.example.demo.dto.CurrencyExchangeDTO;
import com.example.demo.exception.ResourceValidationException;
import com.example.demo.model.CurrencyExchange;
import com.example.demo.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for @{@link CurrencyExchange}
 *
 * @see CurrencyExchangeRepository
 */
@Service
public class CurrencyExchangeService {

    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    public CurrencyExchangeService(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    /**
     * Saves @{@link CurrencyExchange} entity to database
     *
     * @param currencyExchange
     */
    @Transactional
    public void save(CurrencyExchange currencyExchange) throws ResourceValidationException {
        if (currencyExchange.getBase() == null) {
            throw new ResourceValidationException("CurrencyExchange base is null");
        }
        if (currencyExchange.getDate() == null) {
            throw new ResourceValidationException("CurrencyExchange date is null");
        }
        currencyExchangeRepository.save(currencyExchange);
    }

    /**
     * Get last ten and convert to currencyExchangeDTO
     *
     * @return List<CurrencyExchangeDTO>
     */
    @Transactional
    public List<CurrencyExchangeDTO> findLastTen() {
        List<CurrencyExchangeDTO> currencyExchangeDTOS = new ArrayList<>();
        currencyExchangeRepository.findLastTen()
                .forEach(it -> currencyExchangeDTOS
                        .add(convertCurrencyExchangeToDTO(it)));
        return currencyExchangeDTOS;
    }

    /**
     * Convert @{@link CurrencyExchange} to @{@link CurrencyExchangeDTO}
     *
     * @param currencyExchange
     * @return
     */
    private CurrencyExchangeDTO convertCurrencyExchangeToDTO(CurrencyExchange currencyExchange) {
        CurrencyExchangeDTO c = new CurrencyExchangeDTO();
        c.setId(currencyExchange.getId());
        c.setBase(currencyExchange.getBase());
        c.setDate(currencyExchange.getDate());
        c.setRates(currencyExchange.getRates());
        return c;
    }
}
