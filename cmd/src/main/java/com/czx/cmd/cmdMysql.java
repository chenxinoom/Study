package com.czx.cmd;

import java.io.*;

/**
 * 可以实现进入mysql的功能
 */
public class cmdMysql {

    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec("sh");
        OutputStream out = exec.getOutputStream();
        OutputStreamWriter bw = new OutputStreamWriter(out);
        bw.write("ls");

        bw.flush();
        bw.close();
        out.close();
        InputStream in = exec.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        while (br.readLine() != null){
            System.out.println(br.readLine());
        }
        exec.waitFor();
    }
}
