package com.codeanalysis;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reactor reactor = new Reactor(23);
        reactor.run();
    }
}
