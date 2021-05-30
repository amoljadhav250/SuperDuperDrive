package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("note")
public class NoteConroller {

    private UserService userService;
    private NoteService noteService;

    public NoteConroller(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserid();
    }

    @PostMapping("add-note")
    public String addNote(Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model){
        String username = authentication.getName();
        User user=userService.getUser(username);
        Integer userId=user.getUserid();
        System.out.println("noteForm.getNotetitle():= "+noteForm.getNotetitle());
        System.out.println("noteForm.getNotedescription():= "+noteForm.getNotedescription());
        Integer noteId=noteForm.getNoteid();
        String title=noteForm.getNotetitle();
        String desc=noteForm.getNotedescription();
        if(noteService.getNote(noteId)==null){
            Note newNote = new Note( noteId,  title,  desc,  userId);
            noteService.addNote(newNote);
        }else{
            noteService.updateNote(noteId,title,desc);
        }

        model.addAttribute("result",true);
        model.addAttribute("notes",noteService.getNoteListings(userId));
        System.out.println("noteService.getNoteListings(userId):="+noteService.getNoteListings(userId));

        return "result";
    }

    @GetMapping(value = "/get-note/{noteId}")
    public Note getNote(@PathVariable Integer noteId) {
        return noteService.getNote(noteId);
    }

    @GetMapping(value = "/delete-note/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model){
        noteService.deleteNote(noteId);
        Integer userId = getUserId(authentication);
        model.addAttribute("notes", noteService.getNoteListings(userId));
        model.addAttribute("result", "success");

        return "result";
    }


}
