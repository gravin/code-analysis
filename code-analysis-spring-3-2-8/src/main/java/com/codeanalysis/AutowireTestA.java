package com.codeanalysis;

public class AutowireTestA {
    private AutowireTestB autowireTestB;

    public AutowireTestB getAutowireTestB() {
        return autowireTestB;
    }

    public void setAutowireTestB(AutowireTestB autowireTestB) {
        this.autowireTestB = autowireTestB;
    }

    @Override
    public String toString() {
        return "AutowireTestA{" +
                "autowireTestB=" + autowireTestB +
                '}';
    }
}
