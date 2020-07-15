package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Table(name = "Trade")
@Configurable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Trade extends BaseEntity {

    private static Log logger = LogFactory.getLog(Trade.class);
    
    @Transactional(readOnly = true)
    public static List<Trade> getTrades(String party, String symbol, String date) {
    	String sql = "SELECT o FROM Trade o where 1=1 and where o.buyer = ?1 and o.symbol = ?2 and o.date = ?3";
        return entityManager().createQuery(
        		sql,
                Trade.class).setParameter(1, party).setParameter(2, symbol).setParameter(3, date).getResultList();
    }

    public Trade(String seller, String buyer, String symbol, Double price, Date date) {
    	this.seller = seller;
        this.buyer = buyer;
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TradeId")
    private Integer tradeId;

    @Column(name = "Seller", length = 35)
    private String seller;

    @Column(name = "Buyer")
    private String buyer;

    @Column(name = "Symbol", length = 35)
    private String symbol;

    @Column(name = "Price", length = 35)
    private Double price;

    @Column(name = "date")
    private Date date;
    
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
