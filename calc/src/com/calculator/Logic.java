package com.calculator;

public class Logic {
    public static int get_result(int first, int second, char action) {
        int result=0;
        switch (action) {
            case '+' -> result = first + second;
            case '-' -> result = first - second;
            case '*' -> result = first * second;
            case '/' -> result = first / second;
        }

        return result;
    }

}
