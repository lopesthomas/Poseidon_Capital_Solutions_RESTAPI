package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

/**
 * Service class for managing RuleName entities.
 * Provides methods to interact with the RuleNameRepository.
 */
@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Retrieves all {@link RuleName} entities from the repository
     *
     * @return a list of all RuleName entities
     */
    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    /**
     * Saves or update a {@link RuleName} entity to the repository
     *
     * @param ruleName the RuleName entity to save
     */
    public void save(RuleName ruleName) {
        ruleNameRepository.save(ruleName);
    }

    /**
     * Finds a {@link RuleName} entity by its ID
     *
     * @param id the ID of the RuleName entity
     * @return the RuleName entity if found, otherwise null
     */
    public RuleName findById(Integer id) {
        return ruleNameRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a {@link RuleName} entity by its ID
     *
     * @param id the ID of the RuleName entity to delete
     */
    public void delete(Integer id) {
        ruleNameRepository.deleteById(id);
    }

}
