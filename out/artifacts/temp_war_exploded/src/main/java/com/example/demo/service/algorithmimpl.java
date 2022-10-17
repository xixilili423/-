package com.example.demo.service;

import com.example.demo.study.src.Function.Out;
import org.springframework.stereotype.Service;

@Service
public class algorithmimpl implements  algorithm{

   public String inoutput(String url)
    {
        Out out=new Out(url);
        return out.toString();

    }
}
