package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.mapper.Usermapper;
import com.example.demo.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class loginimpl implements login {
    @Autowired
    Usermapper usermapper ;

    List<String> li = new ArrayList<>();

    @Autowired
    public loginimpl(){
        //将利用构造方法自动注入的对象赋值给static
        Usermapper usermapper ;
        List<String> li = new ArrayList<>();
    }

    public user log(String id, String password) {

        QueryWrapper<user> query = new QueryWrapper<>();
        query.eq("id", id).eq("password", password);
        user li = usermapper.selectOne(query);
        if (li.getPassword()== null) {

            return li;
        } else {
            return li;
        }
    }

    public boolean account(String id, String password) {
        QueryWrapper<user> query = new QueryWrapper<>();
        query.eq("id", id).eq("password", password);
        li = (List<String>) usermapper.selectOne(query);
        if (li == null) {
            user u = new user();
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
        QueryWrapper<user> query = new QueryWrapper<>();
        UpdateWrapper<user> update = new UpdateWrapper<>();
        query.eq("id", id);
        List<user> us = usermapper.selectList(query);
        user u = us.get(0);
        if (u == null) {
            return false;

        }
        else {
            user u1 = new user();
            u1.setId(id);
            u1.setPassword(password);
            usermapper.update(u1, update);
            return true;
        }

    }
}
