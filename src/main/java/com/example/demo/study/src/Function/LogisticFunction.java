package com.example.demo.study.src.Function;
import  com.example.demo.study.src.Class.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class LogisticFunction {
    private  double accuracy;
    public  double  Cal(String URL) {
/**
 * 逻辑回归训练
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
        csvList_train1 = csvio.readCsv("D:\\SOURSE\\软件缺陷项目资料\\AEEEM\\csv\\PDE.csv");
        csvList_train2 = csvio.readCsv("D:\\SOURSE\\软件缺陷项目资料\\AEEEM\\csv\\JDT.csv");
        csvList_train3 = csvio.readCsv("D:\\SOURSE\\软件缺陷项目资料\\AEEEM\\csv\\Lucene.csv");
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

        //clean为0，buggy为1
        double[] train_type_double = change.type_change(type_train);
        double[] test_type_double = change.type_change(type_test);

        //标准化
//        dataList_train = Standardization.ZScore(dataList_train);
//        dataList_test = Standardization.ZScore(dataList_test);

        /*
        设置参数
         */
        int sample_num = dataList_train.length;  //训练集样本个数
        int para_num = dataList_train[0].length;  //训练集特征个数
        int test_sample_num = dataList_test.length;  //测试集样本个数
        int test_para_num = dataList_test[0].length;  //测试集特征个数
        double rate = 0.0003;  //学习率
        int maxCycle = 10000;  //迭代次数

        //LR训练模型
        LogisticNode LR = new LogisticNode(dataList_train,train_type_double,para_num,rate,sample_num,maxCycle);
        double[] W = LR.Update(dataList_train,train_type_double,maxCycle,rate);

        /*
        保存模型
         */
        csvio.writeLRCsv("D:\\csvdata\\LR_W.csv",W);

        //预测
        double[] pre = LogisticTest.test(test_para_num,test_sample_num,dataList_test,W);
//        System.out.println(Arrays.toString(pre));

        //计算准确率
        int countNum=0;
        for (int a=0;a<test_type_double.length;a++){
            if (pre[a]==test_type_double[a]){
                countNum++;
            }
        }
        DecimalFormat dF = new DecimalFormat("0.0000");  //精确度

        accuracy = (float)countNum/test_type_double.length;
//        System.out.println("正确率为："+dF.format((float)countNum/test_type_double.length));



        /*
        保存预测结果
         */
        type_result = change.type_change_back(pre);
        //写入csv
        csvio.writeCsv("D:\\SOURSE\\软件缺陷项目资料\\AEEEM\\csv1\\LRresult.csv",type_result);

        //计算得分
        Judge judge = new Judge();
        judge.score(type_test,type_result);

        return accuracy;
    }
}
