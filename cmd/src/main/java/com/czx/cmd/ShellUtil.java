package com.czx.cmd;

import java.io.*;

public class ShellUtil {

    public static void createShell(String path) throws IOException {
        if (path == null){
            System.out.println("路径不能为空");
            return;
        }

        File sh = new File(path);

        sh.createNewFile();
        sh.setExecutable(true);

        BufferedWriter bw = new BufferedWriter(new FileWriter(sh));

        bw.write("#!/bin/sh");
        bw.newLine();
        bw.write("ls");
        bw.newLine();
        bw.write("sudo su -c \"ls\"");
        bw.newLine();
        bw.write("cd /");
        bw.newLine();
        bw.write("ls");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        createShell("oracle.sh");
    }
}
