package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

/**
 * Service class for managing CurvePoint entities.
 * Provides methods to interact with the CurvePointRepository.
 */
@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * Retrieves all {@link CurvePoint} entities from the repository
     *
     * @return a list of all CurvePoint entities
     */
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    /**
     * Saves or update a {@link CurvePoint} entity to the repository
     *
     * @param curvePoint the CurvePoint entity to save
     */
    public void save(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }

    /**
     * Finds a {@link CurvePoint} entity by its ID
     *
     * @param id the ID of the CurvePoint entity to find
     * @return the found CurvePoint entity, or null if not found
     */
    public CurvePoint findById(Integer id) {
        return curvePointRepository.findById(id).orElse(null);
    }

    /**
     * Deletes a {@link CurvePoint} entity by its ID
     *
     * @param id the ID of the CurvePoint entity to delete
     */
    public void delete(Integer id) {
        curvePointRepository.deleteById(id);
    }

}
