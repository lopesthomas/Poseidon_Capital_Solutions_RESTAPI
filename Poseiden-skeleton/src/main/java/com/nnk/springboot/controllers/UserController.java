package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

/**
 * Controller to manage User-related operations
 * Only accessible by users with the ADMIN role
 */
@Controller
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Display all users in the system
     *
     * @param model model to pass users to the view
     * @return the user list view
     */
    @RequestMapping("/user/list")
    public String showUserList(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    /**
     * Show form to add a new user
     *
     * @param user the user object bound to the form
     * @return the add user view
     */
    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    /**
     * Validate and save a new user with encoded password
     *
     * @param user   user object to validate
     * @param result validation result
     * @param model  model to pass back to the view
     * @return redirection or form view if validation fails
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            userService.save(user);
            model.addAttribute("users", userService.findAll());
            return "redirect:/user/list";
        }
        return "user/add";
    }

    /**
     * Display update form for a specific user
     *
     * @param id    user ID to update
     * @param model model to hold user data
     * @return update view or redirect if user not found
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        if (user != null) {
            user.setPassword("");
            model.addAttribute("user", user);
            return "user/update";
        }
        model.addAttribute("error", "User not found");
        
        return "redirect:/user/list";
    }

    /**
     * Update a user after validating the input
     *
     * @param id     ID of the user to update
     * @param user   updated user object
     * @param result validation result
     * @param model  model to pass data
     * @return redirect to user list or return to form on error
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(id);
        userService.save(user);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }

    /**
     * Delete a user by ID
     *
     * @param id    ID of the user to delete
     * @param model model to pass messages
     * @return redirect to user list
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            model.addAttribute("error", "User not found");
            return "redirect:/user/list";
        }
        userService.delete(id);
        model.addAttribute("users", userService.findAll());
        return "redirect:/user/list";
    }
}
