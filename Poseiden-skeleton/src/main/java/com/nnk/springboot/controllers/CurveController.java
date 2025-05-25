package com.nnk.springboot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

import jakarta.validation.Valid;

/**
 * Controller for managing CurvePoint entities.
 * Provides endpoints for listing, creating, updating, and deleting curve points.
 */
@Controller
public class CurveController {
    @Autowired
    private CurvePointService curvePointService;

    /**
     * Displays the list of all curve points.
     *
     * @param model the model to pass data to the view
     * @return the curve point list view
     */
    @RequestMapping("/curvePoint/list")
    public String showCurvePointList(Model model)
    {
        List<CurvePoint> curvePoints = curvePointService.findAll();
        model.addAttribute("curvePoints", curvePoints);

        String remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("remoteUser", remoteUser);
        return "curvePoint/list";
    }

    /**
     * Displays the form to add a new curve point.
     *
     * @param curvePoint the curve point model attribute
     * @return the curve point addition form view
     */
    @GetMapping("/curvePoint/add")
    public String addCurvePointForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    /**
     * Validates and saves a new curve point.
     *
     * @param curvePoint the curve point to validate and save
     * @param result validation results
     * @param model the model to pass data to the view
     * @return redirection to list or back to form if errors
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("curvePoints", curvePoint);
            return "curvePoint/add"; // ou "curvePoint/update" selon le cas
        }
        curvePointService.save(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Displays the form to update an existing curve point.
     *
     * @param id the ID of the curve point
     * @param model the model to pass data to the view
     * @return the curve point update form view
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        if (curvePoint != null) {
            model.addAttribute("curvePoint", curvePoint);
        } else {
            model.addAttribute("error", "CurvePoint not found");
        }
        return "curvePoint/update";
    }

    /**
     * Validates and updates an existing curve point.
     *
     * @param id the ID of the curve point to update
     * @param curvePoint the updated curve point data
     * @param result validation results
     * @param model the model to pass data to the view
     * @return redirection to list or back to form if errors
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointService.save(curvePoint);

        return "redirect:/curvePoint/list";
    }

    /**
     * Deletes a curve point by its ID.
     *
     * @param id the ID of the curve point
     * @param model the model to pass data to the view
     * @return redirection to the curve point list
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurvePoint(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.findById(id);
        if (curvePoint != null) {
            curvePointService.delete(id);
        } else {
            model.addAttribute("error", "CurvePoint not found");
        }
        return "redirect:/curvePoint/list";
    }
}
