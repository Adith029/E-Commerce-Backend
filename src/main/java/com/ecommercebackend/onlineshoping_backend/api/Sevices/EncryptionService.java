package com.ecommercebackend.onlineshoping_backend.api.Sevices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;



import jakarta.annotation.PostConstruct;

@Service
public class EncryptionService {

    @Value("${encryption.salt.rounds}")
    private int saltRounds;
    private String salt;

    @PostConstruct
    public void PostConstruct(){
        salt=BCrypt.gensalt(saltRounds);
    }
    public String encryptPassword(String password){
        return BCrypt.hashpw(password, salt);
    }
    public static boolean verifyPassword(String password,String hash){
        return BCrypt.checkpw(password, hash);
    }
    
}
