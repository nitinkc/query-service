package com.nitin.microservices.query.currencyconversionservice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ConvertedDto {
    private CurrencyConversionBean currencyConversionBean;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
}
