package com.example.demo.study.src.Function;
import com.example.demo.study.src.Class.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class KNNfunction {

    private  double accuracy;

    public  double  Cal(String URL){
        /**
         * KNN训练
         */
        String URL_test  = URL;

        ArrayList<String[]> csvList_train = new ArrayList<String[]>();
        ArrayList<String[]> csvList_train1 = new ArrayList<String[]>();
        ArrayList<String[]> csvList_train2 = new ArrayList<String[]>();
        ArrayList<String[]> csvList_train3 = new ArrayList<String[]>();
        ArrayList<String[]> csvList_test = new ArrayList<String[]>();

        ArrayList<String> type_train = new ArrayList<String>();
        ArrayList<String> type_test = new ArrayList<String>();
        ArrayList<String> type_result = new ArrayList<String>();

        csvIO csvio = new csvIO();
        csvList_train1 = csvio.readCsv("C:\\Users\\lenovo\\Desktop\\aaa\\algorithm\\csvdata\\PDE.csv");
        csvList_train2 = csvio.readCsv("C:\\Users\\lenovo\\Desktop\\aaa\\algorithm\\csvdata\\JDT.csv");
        csvList_train3 = csvio.readCsv("C:\\Users\\lenovo\\Desktop\\aaa\\algorithm\\csvdata\\Lucene.csv");
        for (int i = 0; i < csvList_train1.size(); i++) {
            csvList_train.add(csvList_train1.get(i));
        }
        for (int i = 0; i < csvList_train2.size(); i++) {
            csvList_train.add(csvList_train2.get(i));
        }
        for (int i = 0; i < csvList_train3.size(); i++) {
            csvList_train.add(csvList_train3.get(i));
        }

        csvList_test = csvio.readCsv(URL_test);


         /*
        转换格式
         */
        Exchange change = new Exchange();
        double[][] dataList_train = change.exchange(csvList_train,type_train);
        double[][] dataList_test = change.exchange(csvList_test,type_test);

        //标准化
        dataList_train = Standardization.ZScore(dataList_train);
        dataList_test = Standardization.ZScore(dataList_test);

        //计算距离
        KNNnode knn = new KNNnode();
        int K = 50; //K值
        type_result = knn.calcul(dataList_train,dataList_test,type_train,K);

        //写入csv
        csvio.writeCsv("C:\\Users\\lenovo\\Desktop\\aaa\\algorithm\\csvdata\\KNNresult.csv",type_result);

        //计算正确率
        int countNum=0;
        for (int a=0;a<type_test.size();a++){
            if (Objects.equals(type_test.get(a), type_result.get(a))){
                countNum++;
            }
        }
        DecimalFormat dF = new DecimalFormat("0.0000");  //精确度
        accuracy = (float)countNum/type_test.size();
        //System.out.println(accuracy);
        System.out.println("正确率为："+dF.format((float)countNum/type_test.size()));

        //计算得分
        Judge judge = new Judge();
        judge.score(type_test,type_result);

        return accuracy;
    }

    public double getAccuracy() {
        return accuracy;
    }

}
