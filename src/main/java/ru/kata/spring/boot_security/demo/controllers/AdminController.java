package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminController {
    private UserService userService;
    private RoleService roleService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String allUsers(Model model, Principal principal) {
        model.addAttribute("userAdmin", userService.findByUsername(principal.getName()));
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin_panel";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("newUser", user);
        return "redirect:";
    }

    @PostMapping("/admin/new")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:";
    }


    @GetMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "redirect:";
    }

    @PatchMapping("/admin/edit/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:";
    }

    @DeleteMapping("/admin/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:";
    }
}
