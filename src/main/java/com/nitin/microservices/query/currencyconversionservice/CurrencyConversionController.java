package com.nitin.microservices.query.currencyconversionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nitin on Sunday, November/17/2019 at 10:30 PM
 */
@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @PostMapping("/currency-converter")
    public ResponseEntity<ConvertedDto> convertCurrency(@RequestBody Map<String, Object> body){
        String url = "http://localhost:8000/foreign-exchange";
        HttpEntity<Map<String,Object>> httpEntity = new HttpEntity(body);
        Integer quantity = (Integer) body.get("quantity");

        //Calling the Foreign Exchange Microservice from port 8000
        ResponseEntity<CurrencyConversionBean> responseEntity =
                new RestTemplate()
                        .exchange(url, HttpMethod.POST, httpEntity, CurrencyConversionBean.class);

        CurrencyConversionBean response = responseEntity.getBody();
        logger.info("{}",response);

        ConvertedDto convertedDto = ConvertedDto.builder()
                .currencyConversionBean(response)
                .quantity(BigDecimal.valueOf(quantity))
                .totalCalculatedAmount(response.getConversionMultiple().multiply(BigDecimal.valueOf(quantity)))
                .build();

        return ResponseEntity.ok(convertedDto);
    }

    @PostMapping("/currency-converter-feign")
    public CurrencyConversionBean convertCurrencyFeign(@RequestBody Map<String, Object> body){

        CurrencyConversionBean response = proxy.retrieveExchangeValue(body);
        logger.info("{}",response);
        return response;

    }

    @GetMapping("/currency-converter")
    public String showMessage(){
        String msg = "Use this URL :: " + "\n"+
                "http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1";

        return msg;
    }

    @GetMapping("/")
    public String welcomeMessage(){
        String msg = "Use this URL :: " + "\n"+
                "http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1";
        return msg;
    }
}
