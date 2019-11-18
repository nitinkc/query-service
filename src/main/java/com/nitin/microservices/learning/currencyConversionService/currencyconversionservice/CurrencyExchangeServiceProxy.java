package com.nitin.microservices.learning.currencyConversionService.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by nitinon Sunday, November/17/2019 at 11:54 PM
 */
@FeignClient(name="currency-exchange-service",url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/foreign-exchange/from/{from}/to/{to}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);


    }
