package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String signupView(@ModelAttribute User user, Model model) {
        //System.out.println(userService.getAllUsers());
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model) {
       /* System.out.println(userService.getAllUsers());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getFirstname());
        System.out.println(user.getLastname());*/
     //   System.out.println(user);
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
         //   System.out.println(signupError);
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            System.out.println("Redirecting to login page after successful registration");
            return "redirect:/login?signupSuccess";
        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }

    }

}
