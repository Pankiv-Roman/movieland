package com.pankiv.movieland.web.responce;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NbuCurrencyResponse {
    private String cc; // Currency code (USD, EUR, etc.)
    private Double rate; // Exchange rate
}
