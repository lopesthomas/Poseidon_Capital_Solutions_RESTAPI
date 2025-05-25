package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;

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
 * Controller to manage RuleName entities.
 * Provides endpoints for listing, creating, updating, and deleting rules.
 */
@Controller
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    /**
     * Displays the list of all RuleName entries.
     *
     * @param model the model to pass attributes to the view
     * @return the ruleName list view
     */
    @RequestMapping("/ruleName/list")
    public String showRuleNameList(Model model)
    {
        List<RuleName> ruleNameList = ruleNameService.findAll();
        model.addAttribute("ruleNames", ruleNameList);

        String remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("remoteUser", remoteUser);
        return "ruleName/list";
    }

    /**
     * Displays the form to add a new RuleName.
     *
     * @param ruleName a RuleName object for form binding
     * @return the add form view
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

     /**
     * Validates and saves a new RuleName entry.
     *
     * @param ruleName the RuleName to save
     * @param result validation result
     * @param model the model to pass attributes to the view
     * @return redirect to list if success, or reload form if validation fails
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("ruleName", ruleName);
            return "ruleName/add";
        }
        ruleNameService.save(ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Displays the form to update an existing RuleName.
     *
     * @param id the ID of the RuleName to update
     * @param model the model to pass data to the view
     * @return the update form view or redirect if not found
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        if (ruleName != null) {
            model.addAttribute("ruleName", ruleName);
        } else {
            model.addAttribute("error", "RuleName not found");
            return "redirect:/ruleName/list";
        }
        return "ruleName/update";
    }

    /**
     * Validates and updates an existing RuleName.
     *
     * @param id the ID of the RuleName to update
     * @param ruleName the updated RuleName data
     * @param result validation result
     * @param model the model to pass data to the view
     * @return redirect to list or reload form if validation fails
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if (result.hasFieldErrors()) {
            model.addAttribute("ruleName", ruleName);
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.save(ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Deletes a RuleName entry by ID.
     *
     * @param id the ID of the RuleName to delete
     * @param model the model to pass messages to the view
     * @return redirect to the RuleName list view
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.findById(id);
        if (ruleName != null) {
            ruleNameService.delete(id);
        } else {
            model.addAttribute("error", "RuleName not found");
        }
        return "redirect:/ruleName/list";
    }
}
