package com.czx.cmd;

import java.io.*;

public class SqlUtil {

    public static void createSql(String sqlFilePath,String expdataPath) throws IOException {

        //这个sqlFilePath是默认路径
        //判断路径不能为空
        if (sqlFilePath == null && expdataPath == null){
            System.out.println("路径不能为空");
            return;
        }

        //创建sql文件
        File file = new File(sqlFilePath + "/oracle.sql");
        file.createNewFile();
        File expdataFile = new File(expdataPath);
        if (!expdataFile.exists()){
            expdataFile.mkdir();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));

        bw.write("create or replace directory expdata as " + expdataPath);
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
         createSql("/Users/chenzexin/github","/Users/chenzexin/github/expdata");
    }
}
