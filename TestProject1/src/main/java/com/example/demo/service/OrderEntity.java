package com.example.demo.service;

public class OrderEntity {

    private String party;
    private String orderType;
    private String symbol;
    private Double price;
    private Boolean isMatched;
    
    public Boolean isMatched() {
        return this.isMatched;
    }
    
    public void setMatched(Boolean isMatched) {
        this.isMatched = isMatched;
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
    public String getOrderType() {
        return this.orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getParty() {
        return this.orderType;
    }
    
    public void setParty(String party) {
        this.party = party;
    }
}
