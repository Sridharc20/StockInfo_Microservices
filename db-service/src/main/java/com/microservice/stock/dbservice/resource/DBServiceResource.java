package com.microservice.stock.dbservice.resource;

import com.microservice.stock.dbservice.model.Quote;
import com.microservice.stock.dbservice.model.Quotes;
import com.microservice.stock.dbservice.repository.QuotesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sridhar C on 27/11/17.
 */
@RestController
@RequestMapping("/rest/db")
@CrossOrigin()
public class DBServiceResource {

    private QuotesRepository quotesRepository;

    private DBServiceResource(QuotesRepository quotesRepository){
        this.quotesRepository=quotesRepository;
    }

    @GetMapping("/{userName}")
    public List<String> getQuotesByUserName(@PathVariable("userName") final String userName){
       return quotesRepository.findByUserName(userName).stream().map(quote->{return quote.getQuote();}).collect(Collectors.toList());

    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){

        quotes.getQuotes()
                .stream()
                .forEach(quote->{
                    quotesRepository.save(new Quote(quotes.getUserName(),quote));}
                );

        return getQuotesByUserName(quotes.getUserName());

    }

    @DeleteMapping("/delete/{userName}")
    public List<String> delete(@PathVariable final String userName){

        List<Quote> quotes= quotesRepository.findByUserName(userName);
        quotesRepository.delete(quotes);
        return getQuotesByUserName(userName);
    }


}
