package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

/**
 * Service class for managing {@link Trade} entities.
 * Provide methods to interact with the TradeRepository.
 */
@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    /**
     * Find all trades in the repository
     *
     * @return a list of all {@link Trade} entities
     */
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    /**
     * Save or update a {@link Trade} entity to the repository
     *
     * @param trade the {@link Trade} entity to save
     */
    public void save(Trade trade) {
        tradeRepository.save(trade);
    }

    /**
     * Find a {@link Trade} entity by its ID
     *
     * @param id the ID of the trade to find
     * @return the found {@link Trade} entity, or null if not found
     */
    public Trade findById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    /**
     * Delete a {@link Trade} entity by its ID
     *
     * @param id the ID of the trade to delete
     */
    public void delete(Integer id) {
        tradeRepository.deleteById(id);
    }

}
