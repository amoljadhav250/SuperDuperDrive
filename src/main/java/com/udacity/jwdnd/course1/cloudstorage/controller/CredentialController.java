package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("credential")
public class CredentialController {

    UserService userService;
    CredentialService credentialService;
    EncryptionService encryptionService;

    public CredentialController(UserService userService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("add-cerential")
    public String addCrential(Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model){

        String username = authentication.getName();
        User user=userService.getUser(username);
        Integer userId=user.getUserid();
        System.out.println("credentialForm.getUrl():= "+credentialForm.getUrl());
        System.out.println("credentialForm.getUsername():= "+credentialForm.getUsername());
        Integer credentialid=credentialForm.getCredentialid();
        username = credentialForm.getUsername();
        String url=credentialForm.getUrl();
        String password=credentialForm.getPassword();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        if(credentialService.getCredential(credentialid)==null){
            credentialService.addCredential(url,authentication.getName(),username,encodedKey,encryptedPassword);
        }else{
            credentialService.updateCredential(credentialid,username,url,encodedKey,encryptedPassword);
        }

        model.addAttribute("result",true);
        model.addAttribute("credentials",credentialService.getCredentialListings(userId));
        model.addAttribute("encryptionService",encryptionService);
        return "result";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserid();
    }

    @GetMapping(value = "/get-credential/{credentialId}")
    public Credential getCredential(@PathVariable Integer credentialId,Model model) {
        model.addAttribute("encryptionService",encryptionService);
        return credentialService.getCredential(credentialId);
    }

    @GetMapping
    public String getHome(Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model){
        model.addAttribute("encryptionService",encryptionService);
        return "home";
    }

    @GetMapping(value = "/delete-credential/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId, Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model) {
        credentialService.deleteCredential(credentialId);
        Integer userId = getUserId(authentication);
        model.addAttribute("notes", credentialService.getCredentialListings(userId));
        model.addAttribute("result", "success");
        model.addAttribute("encryptionService",encryptionService);
        return "result";
    }
}

