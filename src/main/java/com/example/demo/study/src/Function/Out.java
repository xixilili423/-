package com.example.demo.study.src.Function;
import java.util.Date;

/**
 * @Auther: houzj
 * @Date: 2022/10/13 - 10 - 13
 * @Description: Function
 * @version: 1.0
 */
public class Out {
    private double accuracy1;
    private double accuracy2;
    private double accuracy3;
    private Date date1;
    private Date date2;
    private Date date3;
    private  String URL;

    public Out(String URL){
        KNNfunction KNN = new KNNfunction();
        LogisticFunction Log = new LogisticFunction();
        LRModel lrModel = new LRModel();
         accuracy1 = KNN.Cal(URL);
         accuracy2 = Log.Cal(URL);
         accuracy3 = lrModel.Cal(URL);
    }

    @Override
    public String toString() {
        String S = "KNN准确率为:"+accuracy1+"\n"+
                "Log准确率为:"+accuracy2+"\n"+
        "lrModel准确率为:"+accuracy3;
        return S;
    }


}
