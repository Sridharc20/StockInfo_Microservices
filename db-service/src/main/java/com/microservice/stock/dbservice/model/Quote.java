package com.microservice.stock.dbservice.model;

import javax.persistence.*;

/**
 * Created by Sridhar C on 27/11/17.
 */
@Entity
@Table(name = "quotes",catalog = "test")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="username")
    private String userName;

    @Column(name="quote")
    private String quote;


    public Quote(String userName, String quote) {
        this.userName = userName;
        this.quote = quote;
    }

    public Quote() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }
}