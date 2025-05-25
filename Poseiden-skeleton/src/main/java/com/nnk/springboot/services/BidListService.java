package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

    @Autowired
    private BidListRepository bidListRepository;

    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    public void save(BidList bidList) {
        bidListRepository.save(bidList);
    }

    public BidList findById(Integer id) {
        return bidListRepository.findById(id).orElse(null);
    }

    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }

}
