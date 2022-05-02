package com.calculator;


import java.util.Arrays;

public class Logic {
    public static String input = "0";
    public static double output = 0d;
    public static final String[] signs = {"+", "-", "÷", "×"};

    private static double one_action(String action, double first, double second) {
        double result =0;
        switch (action) {
            case "+" -> result = first + second;
            case "-" -> result = first - second;
            case "×" -> result = first * second;
            case "÷" -> result = first / second;
        }
        return result;
    }

    public static void change_sign(){
        String[] actions = divide_into_parts();
        if (actions.length>1) {
            if (actions[actions.length - 2].equals("-")) {
                if (actions.length>3){
                    if (actions[actions.length - 4].equals("÷") || actions[actions.length - 4].equals("×")){
                        actions[actions.length - 2] = "";
                    }
                    else actions[actions.length - 2] = "+";
                }
                else actions[actions.length - 2] = "+";

            }
            else if (actions[actions.length - 2].equals("+")) {
                actions[actions.length - 2] = "-";
            }

            else if (Double.parseDouble(actions[actions.length - 1]) % 1 == 0) {
                actions[actions.length - 1] = String.valueOf(Math.round(Double.parseDouble(actions[actions.length - 1]) * -1));
            } else {
                actions[actions.length - 1] = String.valueOf(Double.parseDouble(actions[actions.length - 1]) * -1);
            }
        }
        else if (Double.parseDouble(actions[actions.length - 1]) % 1 == 0) {
            actions[actions.length - 1] = String.valueOf(Math.round(Double.parseDouble(actions[actions.length - 1]) * -1));
        }
        else {
            actions[actions.length - 1] = String.valueOf(Double.parseDouble(actions[actions.length - 1]) * -1);
        }
        input = "";
        for (String action : actions) {
            input = "%s%s".formatted(input, action);
        }

    }
    private static double calculations(String[] actions){
        double result = 0;
        for(int i = 1; i< actions.length; i++){
            if (Arrays.asList(signs).contains(actions[i])) {
                result = one_action(actions[i], Double.parseDouble(actions[0]), Double.parseDouble(actions[i+1]));
            }
        }
        return result;
    }
    public static String equals(){
        output = get_result();
        if (output % 1 == 0)
            input = String.valueOf(Math.round(output));
        else
            input = String.valueOf(output);

        if (Double.isInfinite(output)) {
            input = "0";
            return "Cannot divide by zero";
        }
        else
            return input;
    }
    public static void multiply_divide(String command){
        if (Arrays.asList(signs).contains(input.substring(input.length() - 1))){
            input = input.substring(0, input.length() - 1);
        }
        input += command;
    }
    public static void minus(){
        if (input.charAt(input.length() - 1) == '+'
                || input.charAt(input.length() - 1) == '-'){
            input = input.substring(0, input.length() - 1);
        }
        input += "-";
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
    private static String[] divide_into_parts(){
        String[] actions = new String[99];
        String temp = "";
        int element = 0;

        temp = "%s%s".formatted(temp, input.charAt(0));
        for (int i = 1; i<input.length(); i++){
            if (input.charAt(i)=='+' || input.charAt(i)=='×' || input.charAt(i)=='÷'
                    ||(input.charAt(i)== '-' && input.charAt(i-1)!='+' && input.charAt(i-1)!='×' && input.charAt(i-1)!='÷')){
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

    public static double get_result() {
        String[] actions = divide_into_parts();
        return calculations(actions);
    }
}
