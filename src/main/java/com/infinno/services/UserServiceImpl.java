package com.infinno.services;

import com.infinno.entities.ReferralIdentifier;
import com.infinno.entities.Role;
import com.infinno.entities.User;
import com.infinno.models.bindingModels.RegistrationModel;
import com.infinno.models.viewModels.UserViewModel;
import com.infinno.repositories.ReferralIdentifierRepository;
import com.infinno.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;


    @Autowired
    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private  RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReferralIdentifierRepository referralIdentifierRepository;

    @Override
    public void register(RegistrationModel registrationModel) {
        User user = this.modelMapper.map(registrationModel,User.class);
        String encryptedPassword = this.bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);
        ReferralIdentifier referralIdentifier = new ReferralIdentifier();
        referralIdentifier.setAmount(new BigDecimal("0"));
        referralIdentifier.setDiscountMoney(new BigDecimal("0"));
        referralIdentifier.setReferralCode(UUID.randomUUID().toString());
        referralIdentifier.setUserHash(UUID.randomUUID().toString());
        this.referralIdentifierRepository.save(referralIdentifier);
        user.setReferralIdentifier(referralIdentifier);
        Role role=roleService.getDefaultRole();
        user.addDefaultRole(role);
        this.userRepository.save(user);


    }

    @Override
    public UserViewModel findByUsername(String username) {
        User user = this.userRepository.findOneByUsername(username);
        UserViewModel userViewModel = this.modelMapper.map(user,UserViewModel.class);
        System.out.println(userViewModel.getId());
        ReferralIdentifier referralIdentifier = this.referralIdentifierRepository.findOne(userViewModel.getId());
        System.out.println();
        userViewModel.setDiscountMoney(referralIdentifier.getDiscountMoney()+"лв.");
        userViewModel.setAmount(referralIdentifier.getAmount()+"лв.");
        userViewModel.setDiscountPercent(referralIdentifier.getPercent()+"%");
        return userViewModel;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findOneByUsername(s);
    }

}
