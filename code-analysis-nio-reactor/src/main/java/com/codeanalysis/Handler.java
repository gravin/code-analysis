package com.codeanalysis;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

final class Handler implements Runnable {
    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(256);
    ByteBuffer output = ByteBuffer.allocate(256);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c)
            throws IOException {
        socket = c;
        c.configureBlocking(false);
// Optionally try first read now
        sk = socket.register(sel, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() { /* ... */
        return true;
    }

    boolean outputIsComplete() { /* ... */
        return true;
    }

    void process() { /* ... */
        System.out.println("输入内容 " + getString(input) + " 输出内容 " + getString(output));
    }

    public void run() {
        try {
            if (state == READING) read();
            else if (state == SENDING) send();
        } catch (IOException ex) { /* ... */ }
    }

    void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
// Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) sk.cancel();
    }

    public static String getString(ByteBuffer buffer) {

        buffer.flip();

        Charset charset = null;

        CharsetDecoder decoder = null;

        CharBuffer charBuffer = null;

        try {

            charset = Charset.forName("UTF-8");

            decoder = charset.newDecoder();

            //用这个的话，只能输出来一次结果，第二次显示为空

// charBuffer = decoder.decode(buffer);

            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());

            return charBuffer.toString();

        } catch (Exception ex) {

            ex.printStackTrace();

            return "error";

        }

    }
}