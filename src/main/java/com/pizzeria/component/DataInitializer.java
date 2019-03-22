package com.pizzeria.component;

import com.pizzeria.model.AppUser;
import com.pizzeria.model.UserRole;
import com.pizzeria.repository.AppUserRepository;
import com.pizzeria.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AppUserRepository    appUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // stworzenie uzytkownikow
        // stowrzenie podstawowych uprawnien
        createInitialRoles();
        createInitialUsers();
    }

    private void createInitialUsers() {
        addUser("admin", "admin", "ROLE_USER", "ROLE_ADMIN");
        addUser("user", "user", "ROLE_USER");
    }

    private void addUser(String username, String password, String... roles) {
        Set<UserRole> userRoles = new HashSet<>();
        for (String role:roles) {
            Optional<UserRole> singleRole = userRoleRepository.findByName(role);
            if (singleRole.isPresent()){
                userRoles.add(singleRole.get());
            }
        }

        // 30:44
        // 7:58
        // 8:08


        //wszystkie role zebrane w secie
        Optional<AppUser> searchedAppUser = appUserRepository.findByUsername(username);
        if (!searchedAppUser.isPresent()) {
            AppUser appUser = AppUser.builder()
                    .username(username)
                    .password(password)
                    .roles(userRoles).build();

            appUserRepository.save(appUser);
        }
    }

    private void createInitialRoles() {
        addRole("ROLE_USER");
        addRole("ROLE_ADMIN");
    }


    private void addRole(String name) {
        Optional<UserRole> searchRole = userRoleRepository.findByName(name);
        if (!searchRole.isPresent()) {
            UserRole role = new UserRole();
            role.setName(name );

            userRoleRepository.save(role);
        }
    }
}
