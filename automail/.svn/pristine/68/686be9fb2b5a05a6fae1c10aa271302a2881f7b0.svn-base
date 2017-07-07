package com.bdqn.entity;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Administrator on 2017/7/3.
 */
public class MailAuthenticator extends Authenticator {
    private String userName;
    private String password;


    public MailAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    @Override
    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);
    }
}
