package com.microservice.stock.dbservice.repository;

import com.microservice.stock.dbservice.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * Created by Sridhar C on 27/11/17.
 */
@EnableJpaRepositories
public interface QuotesRepository extends JpaRepository<Quote,Integer> {

    List<Quote> findByUserName(String userName);

}
