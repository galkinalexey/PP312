package com.galkin.PP312.controllers;

import com.galkin.PP312.models.User;
import com.galkin.PP312.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
       model.addAttribute("users", userService.getAllUsers());
       return "all";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
       model.addAttribute("user", userService.getUserById(id));
       return "/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
       return "/new";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/new";
        }
        userService.addNewUser(user);
       return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
       return "/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") long id) {

        if (bindingResult.hasErrors()) {
            return "/edit";
        }

        userService.updateUser(user, id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

}
