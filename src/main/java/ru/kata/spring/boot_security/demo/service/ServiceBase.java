package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class ServiceBase {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public ServiceBase(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void startDB() {
        User user = new User("user", "Ivan", "Ivanov", "ivan@gmail.com", "user");
        User admin = new User("admin", "Oleg", "Petrov", "oleg@mail.ru", "admin");
        User superAdmin = new User("superadmin", "BigAdmin", "BigBig", "bigadmin@gmail.com", "superadmin");
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        Set<Role> superAdminRole = new HashSet<Role>();
        superAdminRole.add(adminRole);
        superAdminRole.add(userRole);
        roleService.addRole(userRole);
        roleService.addRole(adminRole);
        user.setOneRole(userRole);
        admin.setOneRole(adminRole);
        superAdmin.setRoles(superAdminRole);
        userService.addUser(user);
        userService.addUser(admin);
        userService.addUser(superAdmin);
    }
}
