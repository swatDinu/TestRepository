package com.example.demo.domain;

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
@Table(name = "Order")
@Configurable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Order extends BaseEntity {

    private static Log logger = LogFactory.getLog(Order.class);
    
    @Transactional(readOnly = true)
    public static List<Order> getUnmatchedOrders(String party, String symbol) {
    	String sql = "SELECT o FROM Trade o where 1=1 and where o.party = ?1 and o.symbol = ?2 and o.isMatched = false";
        return entityManager().createQuery(
        		sql,
        		Order.class).setParameter(1, party).setParameter(2, symbol).getResultList();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OrderId")
    private Integer orderId;

    @Column(name = "Party", length = 35)
    private String party;

    @Column(name = "OrderType")
    private String orderType;

    @Column(name = "Symbol", length = 35)
    private String symbol;

    @Column(name = "Price", length = 35)
    private Double price;

    @Column(name = "IsMatched")
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
        return this.party;
    }
    
    public void setParty(String party) {
        this.party = party;
    }

}
