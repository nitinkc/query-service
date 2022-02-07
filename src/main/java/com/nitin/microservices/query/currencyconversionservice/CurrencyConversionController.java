package com.nitin.microservices.query.currencyconversionservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){

        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        //Calling the Foreign Exchange Microservice from port 8000
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/foreign-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);

        CurrencyConversionBean response = responseEntity.getBody();
        logger.info("{}",response);

        return new CurrencyConversionBean(response.getId(),from,to, response.getConversionMultiple(),
                quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());

    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){

        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
        logger.info("{}",response);
        return new CurrencyConversionBean(response.getId(),from,to, response.getConversionMultiple(),
                quantity,quantity.multiply(response.getConversionMultiple()),response.getPort());

    }

    @GetMapping("/currency-converter")
    public String showMessage(){
        String msg = "Use this URL :: " + "\n"+
                "http://localhost:8100/currency-converter/from/USD/to/INR/quantity/1";

        return msg;
    }

    @GetMapping("/")
    public String welcomeMessage(){
        String msg = "Hello Query";
        return msg;
    }
}
