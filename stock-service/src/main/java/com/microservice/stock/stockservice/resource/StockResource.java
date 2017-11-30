package com.microservice.stock.stockservice.resource;

//import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sridhar C on 28/11/17.
 */
@RestController
@RequestMapping("/rest/stock")
@CrossOrigin()
public class StockResource {

    @Autowired
    RestTemplate restTemplate;

   /* private YahooFinance yahooFinance;

    public StockResource(){
        this.yahooFinance=new YahooFinance();
    }*/

    @GetMapping("{userName}")
    public List<Quote> getStock(@PathVariable("userName") final String userName){

    //List<String> quotes= restTemplate.getForObject("http://localhost:8300/rest/db/"+userName,List.class);

        ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://db-service/rest/db/" + userName, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
                    });

        List<String> quotes = quoteResponse.getBody();
        System.out.println("quotes"+quotes);
       return quotes
               .stream()
               .map(quote -> {
                   Stock stock = getStockPrice(quote);
                   System.out.println(stock.toString());
                  return new Quote(quote, stock.getQuote().getPrice());
               })
               .collect(Collectors.toList());
    }

    private Stock getStockPrice(String quote) {

        try {
            return YahooFinance.get(quote);
        } catch (IOException e) {
            e.printStackTrace();
            return new Stock(quote);
        }
    }

    private class Quote {
        private String quote;

        private BigDecimal price;

         public Quote(String quote, BigDecimal price) {
            this.quote=quote;
            this.price=price;
        }

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
