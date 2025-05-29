package com.nnk.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nnk.springboot.repositories.UserRepository;

/**
 * Controller for handling user login and authorization errors.
 * Provides endpoints for displaying the login page and handling unauthorized access.
 */
@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Displays the login page.
     *
     * @return ModelAndView for the login view
     */
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    /**
     * Displays a 403 error page when the user is not authorized.
     *
     * @return ModelAndView with error message
     */
    @GetMapping("/403")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        String remoteUser = SecurityContextHolder.getContext().getAuthentication().getName();
        mav.addObject("remoteUser", remoteUser);
        return mav;
    }
}
