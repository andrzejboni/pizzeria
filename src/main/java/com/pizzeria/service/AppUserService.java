package com.pizzeria.service;


import com.pizzeria.model.AppUser;
import com.pizzeria.model.dto.request.RegisterUserRequest;
import com.pizzeria.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private UserRoleService userRoleService;

    public Optional<AppUser> register(RegisterUserRequest registerUserRequest) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(registerUserRequest.getUsername());
        if (optionalAppUser.isPresent()) {
            // nie moge zarejestrować
            return Optional.empty();
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(registerUserRequest.getUsername());
        appUser.setUsername(passwordEncoder.encode(registerUserRequest.getPassword()));
        appUser.setRoles(userRoleService.getDefaultUserRoles());

        return Optional.of(appUserRepository.save(appUser));

    }

}
