package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.web.responce.NbuCurrencyResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class DefaultNbuCurrencyService {
    private static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";

    private final Map<String, Double> currencyRates = new HashMap<>();

    public DefaultNbuCurrencyService() {
        updateCurrencyRates();
    }

    public void updateCurrencyRates() {
        RestTemplate restTemplate = new RestTemplate();
        NbuCurrencyResponse[] rates = restTemplate.getForObject(NBU_API_URL, NbuCurrencyResponse[].class);

        for (NbuCurrencyResponse rate : Objects.requireNonNull(rates)) {
            if ("USD".equals(rate.getCc()) || "EUR".equals(rate.getCc())) {
                currencyRates.put(rate.getCc(), rate.getRate());
            }
        }
        // Add UAH with a conversion rate of 1 as it is the base currency
        currencyRates.put("UAH", 1.0);
    }

    public double getRate(String currency) {
        return currencyRates.getOrDefault(currency, 1.0); // Default to UAH if not found
    }
}
