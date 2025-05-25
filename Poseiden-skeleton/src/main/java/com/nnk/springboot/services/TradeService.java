package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public void save(Trade trade) {
        tradeRepository.save(trade);
    }

    public Trade findById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        tradeRepository.deleteById(id);
    }

}
