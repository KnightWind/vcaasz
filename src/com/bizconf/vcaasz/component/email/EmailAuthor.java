package com.bizconf.vcaasz.component.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthor extends Authenticator{
	String userName=null;   
    String password=null;   
    public EmailAuthor(){   
    }   
    public EmailAuthor(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   

	
}