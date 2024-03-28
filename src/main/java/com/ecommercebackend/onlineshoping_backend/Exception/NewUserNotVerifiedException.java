package com.ecommercebackend.onlineshoping_backend.Exception;

public class NewUserNotVerifiedException extends Exception{

    private boolean newMailsent;

    public NewUserNotVerifiedException(boolean newMailsent) {
        this.newMailsent = newMailsent;
    }

    public boolean isNewMailsent() {
        return newMailsent;
    }
    
}
