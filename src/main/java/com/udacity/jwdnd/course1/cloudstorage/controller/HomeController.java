package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {

    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(UserService userService, NoteService noteService, FileService fileService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHome(Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model){
        String username = authentication.getName();
        User user=userService.getUser(username);
        Integer userId=user.getUserid();
        model.addAttribute("notes",noteService.getNoteListings(userId));
        System.out.println("noteService.getNoteListings(userId):="+noteService.getNoteListings(userId));
        model.addAttribute("credentials",credentialService.getCredentialListings(userId));
        model.addAttribute("files",fileService.getFileListings(userId));
        model.addAttribute("encryptionService",encryptionService);
        return "home";
    }

/*
    @ModelAttribute
    public NoteForm noteForm(){
        return new NoteForm();
    }

    @ModelAttribute
    public CredentialForm credentialForm(){
        return new CredentialForm();
    }
*/

}
