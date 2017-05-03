package com.infinno.services;


import com.infinno.models.bindingModels.RegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    void register(RegistrationModel registrationModel);
}
