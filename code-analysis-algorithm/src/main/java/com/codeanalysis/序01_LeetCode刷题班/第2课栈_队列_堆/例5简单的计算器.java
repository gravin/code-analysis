package com.codeanalysis.序01_LeetCode刷题班.第2课栈_队列_堆;

import java.util.Stack;

/**
 * @author Gavin
 * @date 2020/10/11 0:09
 */
public class 例5简单的计算器 {
    public static final int STATE_BEGIN = 0;
    public static final int NUMBER_STATE = 1;
    public static final int OPERATION_STATE = 2;

    public static int calculate(String str) {
        int state = STATE_BEGIN;
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        int computeFlag = 0;
        int number = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                continue;
            }
            switch (state) {
                case STATE_BEGIN:
                    if (c >= '0' && c <= '9') {
                        state = NUMBER_STATE;
                        computeFlag = 0;
                    } else {
                        state = OPERATION_STATE;
                    }
                    i--;
                    break;
                case NUMBER_STATE:
                    if (c >= '0' && c <= '9') {
                        number = number * 10 + c - '0';
                    } else {
                        numberStack.push(number);
                        number = 0;
                        if (computeFlag == 1) {
                            compute(numberStack, operationStack);
                        }
                        state = OPERATION_STATE;
                        i--;
                    }
                    break;
                case OPERATION_STATE:
                    if (c >= '0' && c <= '9') {
                        state = NUMBER_STATE;
                        i--;
                    } else if (c == '+' || c == '-') {
                        computeFlag = 1;
                        operationStack.push(c);
                    } else if (c == '(') {
                        computeFlag = 0;
                        state = NUMBER_STATE;
                    } else if (c == ')') {
                        compute(numberStack, operationStack);
                    }
                    break;
            }
        }

        if (number != 0) {
            numberStack.push(number);
            compute(numberStack, operationStack);
        }
        if (number == 0 && numberStack.isEmpty()) {
            return 0;
        }
        return numberStack.peek();
    }

    private static void compute(Stack<Integer> numberStack, Stack<Character> operationStack) {
        int number1 = numberStack.pop();
        int number2 = numberStack.pop();
        char operator = operationStack.pop();
        int ret = 0;
        switch (operator) {
            case '+':
                ret = number2 + number1;
                break;
            case '-':
                ret = number2 - number1;
                break;
        }
        numberStack.push(ret);
    }

    ;

    public static void main(String[] args) {
        System.out.println(calculate("1+(2-3)+8-2"));
    }

}
