package com.infinno.services;


import com.infinno.models.bindingModels.RegistrationModel;
import com.infinno.models.viewModels.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    void register(RegistrationModel registrationModel);

    List<UserViewModel> findById(long id);
}
