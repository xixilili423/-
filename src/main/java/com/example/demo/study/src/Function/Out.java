package com.example.demo.study.src.Function;
import java.util.Date;

/**
 * @Auther: houzj
 * @Date: 2022/10/13 - 10 - 13
 * @Description: Function
 * @version: 1.0
 */
public class Out {
    public double accuracy1;
    public double accuracy2;
    public String URL;

    public Out(String URL){
        KNNfunction KNN = new KNNfunction();
        LogisticFunction Log = new LogisticFunction();
         accuracy1 = KNN.Cal(URL);
         accuracy2 = Log.Cal(URL);
    }

    @Override
    public String toString() {
        String S = "KNN准确率为:"+accuracy1+"\n"+
                "Log准确率为:"+accuracy2+"\n";
        return S;
    }

}
