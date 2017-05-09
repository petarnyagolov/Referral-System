package com.infinno.services;


import com.infinno.models.bindingModels.RegistrationModel;
import com.infinno.models.viewModels.CampaignViewModel;
import com.infinno.models.viewModels.UserViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    void register(RegistrationModel registrationModel);

    UserViewModel findByUsername(String username);


}
