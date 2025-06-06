package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

/**
 * Service class for managing BidList entities.
 * Provides methods to interact with the BidListRepository.
 */
@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    /**
     * Retrieves all {@link BidList} entities from the repository
     *
     * @return a list of all {@link BidList} entities
     */
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    /**
     * Saves or update a {@link BidList} entity to the repository
     *
     * @param bidList the {@link BidList} entity to save
     */
    public void save(BidList bidList) {
        bidListRepository.save(bidList);
    }

    /**
     * Finds a {@link BidList} entity by its ID
     *
     * @param id the ID of the {@link BidList} entity to find
     * @return the found {@link BidList} entity, or null if not found
     */
    public BidList findById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a {@link BidList} entity by its ID
     *
     * @param id the ID of the {@link BidList} entity to delete
     */
    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }

}
