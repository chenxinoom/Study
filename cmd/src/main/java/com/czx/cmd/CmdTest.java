package com.czx.cmd;

import java.io.*;

import static jdk.nashorn.internal.runtime.ScriptingFunctions.exec;

public class CmdTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        String[] strings = {"java", "-version"};
        Process process = runtime.exec("mkdir chen");
        InputStream inputStream = process.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        String line ;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null){
            sb.append(line);
        }

        process.waitFor();
        System.out.println(sb.toString());

    }
}
