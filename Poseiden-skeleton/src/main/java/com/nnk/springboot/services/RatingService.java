package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

/**
 * Service class for managing ratings.
 * Provides methods to interact with the RatingRepository.
 */
@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    /**
     * Retrieves all {@link Rating} entities from the repository
     *
     * @return a list of all ratings
     */
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    /**
     * Saves or update a {@link Rating} entity to the repository
     *
     * @param rating the rating to save
     */
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    /**
     * Finds a {@link Rating} entity by its ID
     *
     * @param id the ID of the rating to find
     * @return the found rating, or null if not found
     */
    public Rating findById(Integer id) {
        return ratingRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a {@link Rating} entity by its ID
     *
     * @param id the ID of the rating to delete
     */
    public void delete(Integer id) {
        ratingRepository.deleteById(id);
    }

}
