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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;

import jakarta.validation.Valid;

/**
 * Controller for managing BidList entities.
 * Provides endpoints for listing, creating, updating, and deleting bids.
 */
@Controller
public class BidListController {

    @Autowired
    private BidListService bidListService;

    /**
     * Displays the list of all bids.
     *
     * @param model the model to pass data to the view
     * @return the bid list view
     */
    @RequestMapping("/bidList/list")
    public String showBidList(Model model)
    {
        List<BidList> bidList = bidListService.findAll();
        model.addAttribute("bidList", bidList);

        String remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("remoteUser", remoteUser);
        return "bidList/list";
    }

    /**
     * Displays the form to add a new bid.
     *
     * @param bid the bid model attribute
     * @return the bid addition form view
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Validates and saves a new bid.
     *
     * @param bid the bid to validate and save
     * @param result validation results
     * @param model the model to pass data to the view
     * @return redirection to bid list or back to form if errors
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("bidList", bid);
            return "bidList/add";
        }
        bidListService.save(bid);
        return "redirect:/bidList/list";
    }

    /**
     * Displays the form to update an existing bid.
     *
     * @param id the ID of the bid to update
     * @param model the model to pass data to the view
     * @return the bid update form view
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        if (bidList != null) {
            model.addAttribute("bidList", bidList);
        } else {
            model.addAttribute("error", "Bid not found");
            return "403";
        }
        return "bidList/update";
    }

    /**
     * Validates and updates an existing bid.
     *
     * @param id the ID of the bid to update
     * @param bidList the updated bid data
     * @param result validation results
     * @param model the model to pass data to the view
     * @return redirection to bid list or back to form if errors
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            bidList.setBidListId(id);
            return "bidList/update";
        } else {
            bidList.setBidListId(id);
            bidListService.save(bidList);
        }
        return "redirect:/bidList/list";
    }

    /**
     * Deletes a bid by ID.
     *
     * @param id the ID of the bid to delete
     * @param model the model to pass data to the view
     * @return redirection to bid list
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.findById(id);
        if (bidList != null) {
            bidListService.deleteById(id);
        } else {
            model.addAttribute("error", "Bid not found");
        }
        return "redirect:/bidList/list";
    }
}
