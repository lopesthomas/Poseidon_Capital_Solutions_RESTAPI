package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;

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

import jakarta.validation.Valid;

/**
 * Controller to handle Rating-related web requests.
 * Provides endpoints to list, create, update, and delete ratings.
 */
@Controller
public class RatingController {
    @Autowired
    private RatingService ratingService;

    /**
     * Displays the list of all ratings.
     *
     * @param model the model to pass data to the view
     * @return the view for the rating list
     */
    @RequestMapping("/rating/list")
    public String showRatingList(Model model)
    {
        List<Rating> ratingList = ratingService.findAll();
        model.addAttribute("ratings", ratingList);

        String remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("remoteUser", remoteUser);
        return "rating/list";
    }

    /**
     * Displays the form to add a new rating.
     *
     * @param rating the rating model attribute
     * @return the view for adding a new rating
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    /**
     * Validates and saves a new rating.
     *
     * @param rating the rating to validate and save
     * @param result validation results
     * @param model the model to pass data to the view
     * @return redirect to list or return to form if validation errors
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("rating", rating);
            return "rating/add";
        }
        ratingService.save(rating);
        return "redirect:/rating/list";
    }

    /**
     * Displays the form to update an existing rating.
     *
     * @param id the ID of the rating to update
     * @param model the model to pass data to the view
     * @return the view for updating a rating or error if not found
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        if (rating != null) {
            model.addAttribute("rating", rating);
        } else {
            model.addAttribute("error", "Rating not found");
        }
        return "rating/update";
    }

    /**
     * Validates and updates an existing rating.
     *
     * @param id the ID of the rating to update
     * @param rating the updated rating data
     * @param result validation results
     * @param model the model to pass data to the view
     * @return redirect to list or return to form if validation errors
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("rating", rating);
            return "rating/update";
        }
        rating.setId(id);
        ratingService.save(rating);
        return "redirect:/rating/list";
    }

    /**
     * Deletes an existing rating by ID.
     *
     * @param id the ID of the rating to delete
     * @param model the model to pass error messages to the view
     * @return redirect to rating list
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.findById(id);
        if (rating != null) {
            ratingService.delete(id);
        } else {
            model.addAttribute("error", "Rating not found");
        }
        return "redirect:/rating/list";
    }
}
