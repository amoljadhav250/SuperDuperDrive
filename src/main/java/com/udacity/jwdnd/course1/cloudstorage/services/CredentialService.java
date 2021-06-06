package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

@Service
public class CredentialService {

    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(UserMapper userMapper, CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void addCredential(String url, String userName, String credentialUserName, String key, String password) {
        Integer userId = userMapper.getUser(userName).getUserid();
        Credential credential = new Credential(0, url, credentialUserName, key, password, userId);
        credentialMapper.insert(credential);
    }

    public Credential[] getCredentialListings(Integer userId) {
        return credentialMapper.getCredentialListings(userId);
    }

    public Credential getCredential(Integer credentialId) {
        System.out.println("credentialId:- " + credentialId);
        Credential temp = credentialMapper.getCredential(credentialId);
        if (temp != null) {
            String tempPassword = encryptionService.decryptValue(temp.getPassword(), temp.getKey());
            temp.setPassword(tempPassword);
            System.out.println("temp.getCredentialid():-" + temp.getCredentialid());
        }

        return temp;
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

    public void updateCredential(Integer credentialId, String newUserName, String url, String key, String password) {
        credentialMapper.updateCredential(credentialId, newUserName, url, key, password);
    }

    public Credential getCredentialByUsername(Integer userid, String username){
        return credentialMapper.getCredentialByUsername(userid, username);
    }
}
