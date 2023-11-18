package com.nitin.microservices.query.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * Created by nitin on Sunday, November/17/2019 at 11:54 PM
 */
//@FeignClient(name="currency-exchange-service",url = "localhost:8000")
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="ZUUL-API-GATEWAY-SERVER")
@RibbonClient(name="command-service")
public interface CurrencyExchangeServiceProxy {
    //@GetMapping("/foreign-exchange/from/{from}/to/{to}")
    @PostMapping("command-service/foreign-exchange")
    public CurrencyConversionBean retrieveExchangeValue(@RequestBody Map<String, Object> body);
}
