package com.example.demo.service;

import java.util.Date;
import java.util.List;

import com.example.demo.domain.Order;
import com.example.demo.domain.Trade;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TradeServiceImpl {

    public String getTradeList(String party, String symbol, String date) {
        List<Trade> trList = Trade.getTrades(party, symbol, date);
        List<TradeEntity> tradeList = new Gson().fromJson(new Gson().toJson(trList), new TypeToken<List<TradeEntity>>() {
        }.getType());
        return new Gson().toJson(tradeList);
    }

	public String getUnmatchedOrder(String party, String symbol, String orderJson) {
		List<Order> orList = Order.getUnmatchedOrders(party, symbol);
		List<OrderEntity> orderList = new Gson().fromJson(new Gson().toJson(orList), new TypeToken<List<OrderEntity>>() {
        }.getType());
        return new Gson().toJson(orderList);
	}

	public String createTradeList(String orderJson) {
        List<OrderEntity> orderList = new Gson().fromJson(orderJson, new TypeToken<List<OrderEntity>>() {
        }.getType());
        List<OrderEntity> orList = orderList;
        if(orderList != null) {
            for(OrderEntity order : orList) {
                if(order.getOrderType().equals("SELL")) {
                    OrderEntity matchedOrder = orderList.stream().filter(o -> o.getOrderType().equals("BUY") &&
                            o.getPrice()== order.getPrice() && o.getSymbol()== order.getSymbol())
                    .findFirst().orElse(null);
                    orderList.remove(matchedOrder);
                    orderList.remove(order);
                    if(matchedOrder != null) {                        
                        Trade tr = (new Trade(order.getParty(), matchedOrder.getParty(), order.getSymbol(), 
                        		order.getPrice(), new Date()));
                        tr.persist();
                    }
                    matchedOrder.setMatched(true);
                    order.setMatched(true);
                    orList.add(matchedOrder);
                    orList.add(order);
                }
            }
            orList.addAll(orderList);
        }
        for (OrderEntity order : orList) {
        	Order ordr = new Order();
        	ordr.setParty(order.getParty());
        	ordr.setOrderType(order.getOrderType());
        	ordr.setPrice(order.getPrice());
        	order.setSymbol(order.getSymbol());
        	order.setMatched(order.isMatched());
        	ordr.persist();
        }
		return "Added";
	}

}
