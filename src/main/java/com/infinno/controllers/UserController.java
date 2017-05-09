package com.infinno.controllers;

import com.infinno.entities.ReferralIdentifier;
import com.infinno.entities.User;
import com.infinno.errors.Error;
import com.infinno.models.bindingModels.RegistrationModel;
import com.infinno.models.viewModels.CampaignViewModel;
import com.infinno.models.viewModels.UserViewModel;
import com.infinno.services.RoleService;
import com.infinno.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/info")
    public String getInformation(){
        return "info";
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

    @GetMapping("/profile")
    public String getProfile(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        //System.out.println(user.getUsername());
        UserViewModel view = this.userService.findByUsername(user.getUsername());

        model.addAttribute("view",view);
        return "profile";
    }


}
