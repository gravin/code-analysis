package com.codeanalysis;

public class Interview {

    public static void main(String[] args) {
        String str = "2123.01";
        double res = 0;
        int sign = 1;
        if (str.charAt(0) == '-') {
            sign = -1;
        } else if (str.charAt(0) == '+') {

        } else {
            str = '+' + str;
        }
        int dotIndex = str.indexOf(".") == -1 ? 0 : str.indexOf(".");

        for (int i = 1; i < str.length(); i++) {
            if (dotIndex > i) {
                int positiveLenght = dotIndex - i - 1;
                if (i == 1) {
                    if (Double.MAX_EXPONENT < positiveLenght) {
                        throw new NumberFormatException("");
                    }
                }
                double value = (str.charAt(i) - '0') * Math.pow(10, positiveLenght);
                if (Double.MAX_VALUE - value < res) {
                    throw new NumberFormatException("");
                }


                res = res + (str.charAt(i) - '0') * Math.pow(10, dotIndex - i - 1);
            } else if (dotIndex < i) {
                res = res + (str.charAt(i) - '0') * Math.pow(0.1, i - dotIndex);
            }
        }
        System.out.println(res);
    }
}
