package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;


public class loginimpl implements com.example.demo.service.login {
    @Autowired
    public com.example.demo.mapper.Usermapper usermapper;
    List<String> li = new ArrayList<>();

    public boolean log(String id, String password) {
        QueryWrapper<com.example.demo.pojo.user> query = new QueryWrapper<>();
        query.eq("id", id).eq("password", password);
        com.example.demo.pojo.user li = usermapper.selectOne(query);
        if (li == null) {

            return false;
        } else {
            return true;
        }
    }

    public boolean account(String id, String password) {
        QueryWrapper<com.example.demo.pojo.user> query = new QueryWrapper<>();
        query.eq("id", id).eq("password", password);
        li = (List<String>) usermapper.selectOne(query);
        if (li == null) {
            com.example.demo.pojo.user u = new com.example.demo.pojo.user();
            u.setId(id);
            u.setPassword(password);
            usermapper.insert(u);
            return true;
        } else {

            return false;
        }
    }

    @Override
    public boolean changepassword(String id, String password) {
        QueryWrapper<com.example.demo.pojo.user> query = new QueryWrapper<>();
        UpdateWrapper<com.example.demo.pojo.user> update = new UpdateWrapper<>();
        query.eq("id", id);
        List<com.example.demo.pojo.user> us = usermapper.selectList(query);
        com.example.demo.pojo.user u = us.get(0);
        if (u == null) {
            return false;

        }
        else {
            com.example.demo.pojo.user u1 = new com.example.demo.pojo.user();
            u1.setId(id);
            u1.setPassword(password);
            usermapper.update(u1, update);
            return true;
        }

    }
}
