package com.codeanalysis.netty.ch3;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Gavin
 * @date 2020/7/14
 */
public class Client {
    public static void main(String[] args) throws IOException {

        //发送到8888端口
        Socket socket = new Socket("127.0.0.1", 8888);
        //输出流
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write("i am your father!");
        printWriter.flush();
        //关闭资源
        printWriter.close();
        outputStream.close();
        socket.close();
    }

}
