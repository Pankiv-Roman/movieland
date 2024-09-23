package com.pankiv.movieland.entity;

import lombok.Getter;

@Getter
public enum Currency {
    USD("USD"),
    EUR("EUR"),
    UAH("UAH");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public static Currency fromCode(String code) {
        for (Currency currency : Currency.values()) {
            if (currency.getCode().equalsIgnoreCase(code)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Unsupported currency code: " + code);
    }
}
