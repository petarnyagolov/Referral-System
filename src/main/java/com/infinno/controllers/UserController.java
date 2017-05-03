package com.infinno.controllers;

import com.infinno.errors.Error;
import com.infinno.models.bindingModels.RegistrationModel;
import com.infinno.services.RoleService;
import com.infinno.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute RegistrationModel registrationModel){

        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "register";
        }
        this.userService.register(registrationModel);


        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        if (error!=null){
            model.addAttribute(error, Error.INVALID_CREDENTIALS);
        }

        return "login";
    }


}
