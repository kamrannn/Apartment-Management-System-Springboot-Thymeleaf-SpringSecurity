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

/*    public static boolean isValidPassword(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }*/

    /**
     * This is the controller where or login page is getting viewed
     */
    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    /**
     * This controller will return the registration form
     */
    @GetMapping("/register/form")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }

    /**
     * This controller will get the information from the registration form
     * And it will save it in the database.
     * Here I am also checking the user is entering the unique username or not
     * Everytime a request will come, am checking whether a user exists with that
     * username or not.
     */

    @PostMapping("/register/user")
    public String register(@Valid User user, BindingResult result, Model model) {
        /*String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        boolean validPassword = isValidPassword(user.getPassword(), regex);*/

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
