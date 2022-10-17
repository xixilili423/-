package com.example.demo;


import com.example.demo.mapper.Usermapper;
import com.example.demo.pojo.user;
import com.example.demo.service.loginimpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.boot.test.context.SpringBootTest(classes = DemoApplication.class)
public class SpringBootTest {
    @Autowired
    public Usermapper usermapper;
    @Autowired
    loginimpl userBiz;

    user US=new user();

    @Test
    public void test()
    {
        //调用UserBiz的getUser方法，根据用户名和密码获取对应的用户对象
        boolean user = userBiz.log("2" ,"123456");


    }

}
