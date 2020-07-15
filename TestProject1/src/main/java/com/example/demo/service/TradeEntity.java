package com.example.demo.service;

import java.util.Date;

public class TradeEntity {

    private String seller;
    private String buyer;
    private String symbol;
    private Double price;
    private Date date;
   
    public TradeEntity(String seller, String buyer, String symbol, Double price, Date date) {
        this.seller = seller;
        this.buyer = buyer;
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getBuyer() {
        return this.buyer;
    }
    
    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }
    public String getSeller() {
        return this.seller;
    }
    
    public void setSeller(String seller) {
        this.seller = seller;
    }
    
}
