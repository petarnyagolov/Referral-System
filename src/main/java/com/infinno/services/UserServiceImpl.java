package com.infinno.services;

import com.infinno.entities.ReferralIdentifier;
import com.infinno.entities.User;
import com.infinno.models.bindingModels.RegistrationModel;
import com.infinno.repositories.ReferralIdentifierRepository;
import com.infinno.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        referralIdentifier.setReferralCode(UUID.randomUUID().toString());
        referralIdentifier.setUserHash(UUID.randomUUID().toString());
        this.referralIdentifierRepository.save(referralIdentifier);
        user.setReferralIdentifier(referralIdentifier);
        this.userRepository.save(user);


    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findOneByUsername(s);
    }

}
