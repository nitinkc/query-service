package com.nitin.microservices.learning.currencyConversionService.currencyconversionservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by nitin on Sunday, November/17/2019 at 10:30 PM
 */
@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){

        return new CurrencyConversionBean(1L,from,to,BigDecimal.ONE,quantity,quantity,0);

    }

    @GetMapping("/currency-converter")
    public String showMessage(){
        String msg = "Use this URL :: " + "\n"+
                "http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1";

        return msg;
    }
}
