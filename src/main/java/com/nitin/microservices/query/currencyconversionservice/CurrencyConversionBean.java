package com.nitin.microservices.query.currencyconversionservice;

import lombok.*;

import java.math.BigDecimal;

/**
 * Created by nitin on Sunday, November/17/2019 at 10:33 PM
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class CurrencyConversionBean {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private int port;
}
