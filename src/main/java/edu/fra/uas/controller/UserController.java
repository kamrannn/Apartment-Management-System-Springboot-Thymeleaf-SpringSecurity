package edu.fra.uas.controller;

import edu.fra.uas.model.User;
import edu.fra.uas.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "Login";
    }


    @GetMapping("/register/form")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/register/user")
    public String register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Register";
        } else if (userService.findUserByUserName(user.getUsername()).isPresent()) {
            model.addAttribute("isPresent", true);
            return "Register";
        }
        userService.save(user);
        return "Login";
    }
}
