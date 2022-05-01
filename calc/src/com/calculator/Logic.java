package com.calculator;

public class Logic {
    public static int result=0;
    public static int output=0;

    public static int get_result(String input) {
        int i=0;
        int first = 0;
        int second = 0;
        StringBuilder temp = new StringBuilder();
        while (input.charAt(i)!='+' && input.charAt(i)!= '-' && input.charAt(i)!='*' && input.charAt(i)!='/'){
            temp.append(input.charAt(i));
            i++;
        }
        first = Integer.parseInt(temp.toString());
        temp = new StringBuilder();

        char action = input.charAt(i);
        while (i<input.length()){
            temp.append(input.charAt(i));
            i++;
        }
        second = Integer.parseInt(temp.toString());

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
