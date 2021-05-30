package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("file")
public class FileController {

    UserService userService;
    FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("upload-file")
    public String uploadFile(Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model) throws IOException {
        String username = authentication.getName();
        User user = userService.getUser(username);
        Integer userId = user.getUserid();
        MultipartFile file = fileForm.getFile();
        String fileName = file.getOriginalFilename();
        String[] files = fileService.getFileListings(userId);
        boolean flag = false;
        System.out.println(files);
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(fileName)) {
                flag = true;
                break;
            }
        }
        if (flag == true) {
            model.addAttribute("message", "You have tried to add file with same name");
            model.addAttribute("result", false);
            return "result";
        }
        if (fileName.equals("") || fileName == null) {
            model.addAttribute("message", "not saved");
            model.addAttribute("result", false);
            return "result";
        }
        fileService.addFile(file, username);
        model.addAttribute("files", fileService.getFileListings(userId));
        model.addAttribute("result", true);
        return "result";
    }

    private Integer getUserId(Authentication authentication) {
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserid();
    }

    @GetMapping(value = "/delete-file/{filename}")
    public String deleteFile(@PathVariable String filename, Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model) {
        fileService.deleteFile(filename);
        Integer userId = getUserId(authentication);
        model.addAttribute("notes", fileService.getFileListings(userId));
        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping(value = "/get-file/{filename}")
    public String getFile(@PathVariable String filename, Authentication authentication, FileForm fileForm, CredentialForm credentialForm, NoteForm noteForm, Model model) {
        fileService.getFile(filename);
        Integer userId = getUserId(authentication);
        model.addAttribute("notes", fileService.getFileListings(userId));
        model.addAttribute("result", "success");
        return "home";
    }
}
