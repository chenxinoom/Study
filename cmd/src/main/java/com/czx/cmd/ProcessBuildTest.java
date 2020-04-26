package com.czx.cmd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProcessBuildTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> cmd = new ArrayList<String>();

        cmd.add("echo");
        cmd.add("\'whoami\'");

        ProcessBuilder processBuilder = new ProcessBuilder(cmd).redirectErrorStream(true);

        //查看工作目录
        processBuilder.directory(new File("/"));
        Map<String, String> environment = processBuilder.environment();
        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuffer sb = new StringBuffer();
        while ((line=br.readLine()) != null){
            sb.append(line);
        }

        File file = new File("/Users/chenzexin");
        file.createNewFile();
        file.setExecutable(true);
        process.waitFor();
        System.out.println(sb.toString());
//        System.out.println(directory);
        System.out.println(environment);
    }
}
