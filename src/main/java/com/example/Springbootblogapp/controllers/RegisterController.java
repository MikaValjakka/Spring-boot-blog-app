package com.example.Springbootblogapp.controllers;

import com.example.Springbootblogapp.models.Account;
import com.example.Springbootblogapp.repositories.AuthorityRepository;
import com.example.Springbootblogapp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityRepository authorityRepository;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        Account newAccount = new Account();
        model.addAttribute("account", newAccount);
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute Account newAccount){

        accountService.saveAccount(newAccount);


        return "redirect:/";
    }
}
