package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

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
 * Controller for handling Trade-related operations.
 * Includes endpoints for listing, adding, updating, and deleting trades.
 */
@Controller
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * Displays the list of all trades.
     *
     * @param model model to pass data to the view
     * @return the trade list view
     */
    @RequestMapping("/trade/list")
    public String showTradeList(Model model)
    {
        List<Trade> tradeList = tradeService.findAll();
        model.addAttribute("trades", tradeList);

        String remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("remoteUser", remoteUser);
        return "trade/list";
    }

    /**
     * Displays the add trade form.
     *
     * @param trade the trade model to bind to the form
     * @return the add view
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Trade bid) {
        return "trade/add";
    }

    /**
     * Validates and saves a new trade.
     *
     * @param trade  trade entity to validate
     * @param result binding result for validation
     * @param model  model for view rendering
     * @return redirect to list or return to form on validation error
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("trade", trade);
            return "trade/add";
        }
        tradeService.save(trade);
        return "redirect:/trade/list";
    }

    /**
     * Displays the update form for a specific trade.
     *
     * @param id    ID of the trade to update
     * @param model model for view rendering
     * @return the update view or error redirect
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id);
        if (trade != null) {
            model.addAttribute("trade", trade);
        } else {
            model.addAttribute("error", "Trade not found");
        }
        return "trade/update";
    }

    /**
     * Validates and updates a trade.
     *
     * @param id     ID of the trade to update
     * @param trade  trade data
     * @param result validation result
     * @param model  model for view rendering
     * @return redirect to list or return to form if error
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("trade", trade);
            return "trade/update";
        }
        trade.setId(id);
        tradeService.save(trade);
        return "redirect:/trade/list";
    }

    /**
     * Deletes a trade by ID.
     *
     * @param id    ID of the trade to delete
     * @param model model for error messaging
     * @return redirect to list
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.findById(id);
        if (trade != null) {
            tradeService.delete(id);
        } else {
            model.addAttribute("error", "Trade not found");
        }
        return "redirect:/trade/list";
    }
}
