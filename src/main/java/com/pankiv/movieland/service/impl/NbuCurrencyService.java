package com.pankiv.movieland.service.impl;

import com.pankiv.movieland.entity.Currency;
import com.pankiv.movieland.web.responce.NbuCurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

@Service
public class NbuCurrencyService {

    private static final String NBU_API_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private final Map<Currency, Double> currencyRates = new EnumMap<>(Currency.class);
    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(NbuCurrencyService.class);

    public NbuCurrencyService() {
        this.webClient = WebClient.builder().baseUrl(NBU_API_URL).build();
        updateCurrencyRates();
    }

    @Scheduled(zone = "3.0", cron = "0 * * * * MON-FRI")
    public void updateCurrencyRates() {
        try {
            NbuCurrencyResponse[] rates = webClient.get()
                    .retrieve()
                    .bodyToMono(NbuCurrencyResponse[].class)
                    .block();

            for (NbuCurrencyResponse rate : Objects.requireNonNull(rates)) {
                try {
                    Currency currency = Currency.fromCode(rate.getCurrencyCode());
                    if (currency == Currency.USD || currency == Currency.EUR) {
                        currencyRates.put(currency, rate.getRate());
                    }
                } catch (IllegalArgumentException e) {
                    logger.warn("Unsupported currency found in NBU response: {}", rate.getCurrencyCode());
                }
            }
            logger.info("Exchange rate data successfully retrieved and updated.");
        } catch (WebClientResponseException e) {
            logger.error("An error occurred while retrieving data from NBU API: Status {}, Message: {}", e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("General error when updating currency rates: {}", e.getMessage());
        }
        currencyRates.put(Currency.UAH, 1.0);
    }

    public double getRate(Currency currency) {
        return currencyRates.getOrDefault(currency, 1.0);
    }

    public double getRate(String currencyCode) {
        Currency currency = Currency.fromCode(currencyCode);
        return getRate(currency);
    }
}
