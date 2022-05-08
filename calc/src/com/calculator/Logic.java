package com.calculator;

import java.util.Arrays;

public class Logic {
    public static String input = "0";
    public static final String[] signs = {"+", "-", "÷", "×"};

    private static double one_action(String action, double first, double second) {
        switch (action) {
            case "+" -> {return first + second;}
            case "-" -> {return first - second;}
            case "×" -> {return first * second;}
            case "÷" -> {return first / second;}
            case "x²" -> {return first * first;}
        }
        return first;
    }

    public static void backspace(){
        if(input.length()>1)
            input = input.substring(0, input.length() - 1);
        else
            input = "0";
    }

    public static void enter_numbers(String num){
        if (input.equals("0"))
            input = "";
        input += (num);
    }

    public static void percent(){
        String[] actions = divide_into_parts();
        int len = actions.length;
        if (len > 2) {
            if (actions[len - 1].equals("")) {
                actions[len - 1] = String.valueOf(Double.parseDouble(actions[len - 3])/ 100*Double.parseDouble(actions[len - 3]));
            } else {
                actions[len - 1] = String.valueOf(Double.parseDouble(actions[len - 3]) / 100 * Double.parseDouble(actions[len - 1]));
            }
            actions_to_input(actions);
        }
        else
            input = "0";
    }

    public static void change_sign(){
        String[] actions = divide_into_parts();
        int len = actions.length;
        if (len>1) {
            if (!Arrays.asList(signs).contains(actions[len - 1])) {
                if (actions[len - 2].equals("-")) {
                    if (len > 3) {
                        if (actions[len - 4].equals("÷") || actions[len - 4].equals("×"))
                            actions[len - 2] = "";
                        else
                            actions[len - 2] = "+";
                    } else actions[len - 2] = "+";
                } else if (actions[len - 2].equals("+")) {
                    actions[len - 2] = "-";
                } else if (Double.parseDouble(actions[len - 1]) % 1 == 0) {
                    actions[len - 1] = String.valueOf(Math.round(Double.parseDouble(actions[len - 1]) * -1));
                } else {
                    actions[len - 1] = String.valueOf(Double.parseDouble(actions[len - 1]) * -1);
                }
            }
        } else if (Double.parseDouble(actions[len - 1]) % 1 == 0) {
            actions[len - 1] = String.valueOf(Math.round(Double.parseDouble(actions[len - 1]) * -1));
        } else
            actions[len - 1] = String.valueOf(Double.parseDouble(actions[len - 1]) * -1);
        actions_to_input(actions);
    }

    public static void multiply_divide(String command){
        if (Arrays.asList(signs).contains(input.substring(input.length() - 1)))
            input = input.substring(0, input.length() - 1);
        input += command;
        if(input.length() > 1){
            if(input.charAt(input.length() - 2) == '×'
                    || input.charAt(input.length() - 2) == '÷'){
                input = input.substring(0, input.length() - 1);
            }
        }
    }

    public static void minus(){
        if (input.charAt(input.length() - 1) == '+'
                || input.charAt(input.length() - 1) == '-'){
            input = input.substring(0, input.length() - 1);
        }
        input += "-";
    }

    private static void actions_to_input(String[] actions){
        input = "";
        for (String action : actions) {
            input = "%s%s".formatted(input, action);
        }
    }
    public static void degree(){
        if (!Arrays.asList(signs).contains(input.substring(input.length() - 1))) {
            String[] actions = divide_into_parts();
            int len = actions.length;
            actions[len-1] = String.valueOf(one_action("x²", Double.parseDouble(actions[len-1]), 0));
            actions_to_input(actions);
        }
    }

    public static void plus(){
        if (Arrays.asList(signs).contains(input.substring(input.length() - 1))) {
            input = input.substring(0, input.length() - 1);
        }
        input += "+";
        if(input.length() > 1){
            if(input.charAt(input.length() - 2) == '×'
                    || input.charAt(input.length() - 2) == '÷'){
                input = input.substring(0, input.length() - 1);
            }
        }
    }


    private static double calculations(String[] actions){
        double result = Double.parseDouble(actions[0]);
        for(int i = 1; i< actions.length; i++){
            if (Arrays.asList(signs).contains(actions[i]))
                result = one_action(actions[i], result, Double.parseDouble(actions[i+1]));
        }
        return result;
    }

    public static String get_result(){
        String[] actions = divide_into_parts();
        double result = calculations(actions);
        if (result % 1 == 0)
            input = String.valueOf(Math.round(result));
        else
            input = String.valueOf(result);
        if (Double.isInfinite(result)) {
            input = "0";
            return "Cannot divide by zero";
        }
        else
            return input;
    }

    private static String[] divide_into_parts(){
        String[] actions = new String[99];
        String temp = "";
        int element = 0;

        temp = "%s%s".formatted(temp, input.charAt(0));
        for (int i = 1; i<input.length(); i++){
            if (input.charAt(i)=='+' || input.charAt(i)=='×' || input.charAt(i)=='÷'
                    ||(input.charAt(i)== '-' && !Arrays.asList(signs).contains(actions[i-1]))){
                actions[element] = temp;
                actions[element+1] = String.valueOf(input.charAt(i));
                element+=2;
                temp="";
            }
            else {
                temp = "%s%s".formatted(temp, input.charAt(i));
            }
        }
        actions[element] = temp;

        String[] result = new String[element+1];
        System.arraycopy(actions, 0, result, 0, element + 1);

        return result;
    }
}
