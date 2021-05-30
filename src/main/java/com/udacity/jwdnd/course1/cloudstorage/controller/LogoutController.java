package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class LogoutController {

    @PostMapping("logout")
    public String logoutView(Model model) {
        model.addAttribute("loggedOut", true);
        return "redirect:/login?logout";
    }

}
