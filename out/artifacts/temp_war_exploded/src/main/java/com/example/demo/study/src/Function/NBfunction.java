package com.example.demo.study.src.Function;

import com.example.demo.study.src.Class.Exchange;
import com.example.demo.study.src.Class.Standardization;
import com.example.demo.study.src.Class.csvIO;

import java.util.ArrayList;

public class NBfunction {

    public static void main(String[] args){
        /**
         * 朴素贝叶斯训练
         */
        ArrayList<String[]> csvList_train = new ArrayList<String[]>();
        ArrayList<String[]> csvList_test = new ArrayList<String[]>();

        ArrayList<String> type_train = new ArrayList<String>();
        ArrayList<String> type_test = new ArrayList<String>();
        ArrayList<String> type_result = new ArrayList<String>();

        csvIO csvio = new csvIO();
        csvList_train = csvio.readCsv("C:\\Users\\lenovo\\Desktop\\aaa\\algorithm\\csvdata\\PDE.csv");
        csvList_test = csvio.readCsv("C:\\Users\\lenovo\\Desktop\\aaa\\algorithm\\csvdata\\Lucene.csv");

        Exchange change = new Exchange();
        double[][] dataList_train = change.exchange(csvList_train,type_train);
        double[][] dataList_test = change.exchange(csvList_test,type_test);

        //标准化
        dataList_train = Standardization.ZScore(dataList_train);
        dataList_test = Standardization.ZScore(dataList_test);

}
}
