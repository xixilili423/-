package com.example.demo;


import com.example.demo.mapper.Usermapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.boot.test.context.SpringBootTest(classes = com.example.demo.DemoApplication.class)
public class SpringBootTest {

    @Autowired
    public Usermapper usermapper;
    @Test
    public void test()
    {

    }

}
