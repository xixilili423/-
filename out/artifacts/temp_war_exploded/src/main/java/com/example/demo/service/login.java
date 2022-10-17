package com.example.demo.service;

import com.example.demo.mapper.Usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface login {


    boolean log(String id, String password);
    boolean account(String id,String password);
    boolean changepassword(String id,String password);
    @Autowired
    Usermapper usermapper = null;
}
