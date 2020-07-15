package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.TradeServiceImpl;


public class TestController {

    Logger logger = Logger.getLogger(getClass());
    
    @Autowired
    TradeServiceImpl tradeServiceImpl;
    
    @RequestMapping(value = "/createTrade", method = RequestMethod.GET)
    public String createTrade(@RequestParam(value = "orderJson", required = false) String orderJson,
            HttpServletRequest httpServletRequest, HttpServletRequest httpServletResponse) {
        try {
            return tradeServiceImpl.createTradeList(orderJson);

        } catch (Exception e) {
            logger.error("Error occurred while fetching all workflow modules", e);
            return "FAILED";
        }

    }
    
    @RequestMapping(value = "/getTradeList", method = RequestMethod.GET)
    public String getTradeList(@RequestParam(value = "party", required = false) String party,
            @RequestParam(value = "symbol", required = false) String symbol,
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "orderJson", required = false) String orderJson,
            HttpServletRequest httpServletRequest, HttpServletRequest httpServletResponse) {
        try {
            return tradeServiceImpl.getTradeList(party, symbol, date, orderJson);

        } catch (Exception e) {
            logger.error("Error occurred while fetching all workflow modules", e);
            return "FAILED";
        }

    }
    
    @RequestMapping(value = "/getUnmatched", method = RequestMethod.GET)
    public String getUnmatchedOrder(@RequestParam(value = "party", required = false) String party,
            @RequestParam(value = "symbol", required = false) String symbol,
            @RequestParam(value = "orderJson", required = false) String orderJson,
            HttpServletRequest httpServletRequest, HttpServletRequest httpServletResponse) {
        try {
            return tradeServiceImpl.getUnmatchedOrder(party, symbol, orderJson);

        } catch (Exception e) {
            logger.error("Error occurred while fetching all workflow modules", e);
            return "FAILED";
        }

    }
}
