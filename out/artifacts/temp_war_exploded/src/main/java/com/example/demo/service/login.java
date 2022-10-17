package com.example.demo.service;

import com.example.demo.mapper.Usermapper;
import com.example.demo.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;


public interface login {


    user log(String id, String password);
    boolean account(String id,String password);
    boolean changepassword(String id,String password);
    @Autowired
    Usermapper usermapper = null;
}
