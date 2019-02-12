package com.example.demo.connection;

import com.example.demo.model.CurrencyExchange;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * HttpConnection class
 *
 * @see com.example.demo.scheduller.ScheduledTasks
 */
public class HttpConnection {

    private static final Logger log = LoggerFactory.getLogger(HttpConnection.class);

    /**
     * Read JSON CurrencyExchange object from url
     *
     * @param urlToRead
     * @return CurrencyExchange if success or null if error occurred
     */
    public static CurrencyExchange getCurrencyExchange(String urlToRead) {
        ObjectMapper objectMapper = new ObjectMapper();
        CurrencyExchange jsonObject = null;
        try {
            URL url = new URL(urlToRead);
            jsonObject = objectMapper.readValue(url, CurrencyExchange.class);
        } catch (IOException e) {
            log.error("getJson IOException", e);
        }
        return jsonObject;
    }
}
