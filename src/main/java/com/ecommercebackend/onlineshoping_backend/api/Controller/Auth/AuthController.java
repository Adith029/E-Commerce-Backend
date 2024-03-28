package com.ecommercebackend.onlineshoping_backend.api.Controller.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommercebackend.onlineshoping_backend.Exception.EmailFailureException;
import com.ecommercebackend.onlineshoping_backend.Exception.NewUserNotVerifiedException;
import com.ecommercebackend.onlineshoping_backend.Exception.UserAllreadyExistsException;
import com.ecommercebackend.onlineshoping_backend.Models.LocalUser;
import com.ecommercebackend.onlineshoping_backend.api.Model.LoginBody;
import com.ecommercebackend.onlineshoping_backend.api.Model.LoginResponse;
import com.ecommercebackend.onlineshoping_backend.api.Model.RegistrationBody;
import com.ecommercebackend.onlineshoping_backend.api.Sevices.UserRegService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/auth")
public class AuthController {

    public UserRegService userRegService;

    public AuthController(UserRegService userRegService) {
        this.userRegService = userRegService;
    }

    @PostMapping("/reg")
    public ResponseEntity register(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userRegService.userRegistration(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserAllreadyExistsException e) {
            
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch (EmailFailureException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
    String jwt = null;
    try {
        jwt = userRegService.loginUser(loginBody);
    } catch (NewUserNotVerifiedException ex) {
        LoginResponse response = new LoginResponse();
        response.setSuccess(false);
        String reason = "USER_NOT_VERIFIED";
        if (ex.isNewMailsent()) {
            reason += "_EMAIL_RESENT";
        }
        response.setFailureReason(reason);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    } catch (EmailFailureException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    if (jwt == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    } else {
        LoginResponse response = new LoginResponse();
        response.setJwt(jwt);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }
}
    @PostMapping("/verify")
    public ResponseEntity verifyEmail(@RequestParam String token){
        if(userRegService.verifyUser(token)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/me")
    public LocalUser getLoggedUserProfile(@AuthenticationPrincipal LocalUser user) {
        return user;
    }
    
    
    
}
