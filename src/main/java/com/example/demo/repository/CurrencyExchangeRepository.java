package com.example.demo.repository;

import com.example.demo.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for working with @{@link CurrencyExchange} entity
 */
@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {

    /**
     * Select last 10 records from database
     *
     * @return
     */
    @Query(value = "SELECT * FROM currency_exchange ORDER BY id DESC LIMIT 10", nativeQuery = true)
    List<CurrencyExchange> findLastTen();
}
