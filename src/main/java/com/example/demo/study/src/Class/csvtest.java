package com.example.demo.study.src.Class;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.*;
import cn.hutool.core.util.CharsetUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class csvtest {
    public ArrayList<String[]> readCsv(String csvFilePath) {
        ArrayList<String[]> csvList = null;
        csvList = new ArrayList<String[]>();

        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(csvFilePath), CharsetUtil.CHARSET_GBK);
        List<CsvRow> rows = data.getRows();

        for (CsvRow row : rows) {//逐行转换
            csvList.add(row.getRawList().toArray(new String[row.getRawList().size()]
            ));
        }
        csvList.remove(0);//去除表头

        return csvList;

    }

    public void writeCsv(String csvFilePath, ArrayList<String> type_result) {
        // 创建CSV写对象
        //CsvWriter csvWriter = new CsvWriter(csvFilePath,',', StandardCharsets.UTF_8);
        CsvWriter csvWriter = new CsvWriter(csvFilePath);

        // 写表头
        String[] headers = {"class"};
        csvWriter.write(headers);
        String[] content = {""};
        for (String s : type_result) {
            content[0] = s;
            csvWriter.write(content);
        }
        csvWriter.close();
    }

    public void writeLRCsv(String csvFilePath, double[] file) {  //保存逻辑回归模型
        CsvWriter csvWriter = new CsvWriter(csvFilePath);
        String[] str = new String[file.length];
        for (int i = 0; i < file.length; i++) {
            str[i] = String.valueOf(file[i]);
        }
//        System.out.println(Arrays.toString(str));
        csvWriter.write(str);
        csvWriter.close();

    }

    public ArrayList<String[]> readW(String csvFilePath) {  //读取训练模型
        ArrayList<String[]> csvList = null;
        csvList = new ArrayList<String[]>();

        CsvReader reader = CsvUtil.getReader();
        //从文件中读取CSV数据
        CsvData data = reader.read(FileUtil.file(csvFilePath), CharsetUtil.CHARSET_GBK);
        List<CsvRow> rows = data.getRows();

        for (CsvRow row : rows) {//逐行转换
            csvList.add(row.getRawList().toArray(new String[row.getRawList().size()]
            ));
        }

        return csvList;
    }


    public static void main(String[] args){
        csvtest ct = new csvtest();
        ArrayList<String[]> csvList = null;
        csvList = ct.readCsv("D:\\SOURSE\\shixun-group14-main\\csvdata\\JDT.csv");
    }
}
