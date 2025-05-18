package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
public class TradeController {
    // TODO: Inject Trade service
    @Autowired
    private TradeRepository tradeRepository;

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        // TODO: find all Trade, add to model
        List<Trade> tradeList = tradeRepository.findAll();
        model.addAttribute("trades", tradeList);

        String remoteUser = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("remoteUser", remoteUser);
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list
        if (result.hasFieldErrors()) {
            model.addAttribute("trade", trade);
            return "trade/add";
        }
        tradeRepository.save(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form
        Trade trade = tradeRepository.findById(id).orElse(null);
        if (trade != null) {
            model.addAttribute("trade", trade);
        } else {
            model.addAttribute("error", "Trade not found");
        }
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        if (result.hasFieldErrors()) {
            model.addAttribute("trade", trade);
            return "trade/update";
        }
        trade.setId(id);
        tradeRepository.save(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        Trade trade = tradeRepository.findById(id).orElse(null);
        if (trade != null) {
            tradeRepository.delete(trade);
        } else {
            model.addAttribute("error", "Trade not found");
        }
        return "redirect:/trade/list";
    }
}
