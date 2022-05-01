package com.calculator;


import java.util.Arrays;

public class Logic {
    public static String input = "0";
    public static double output = 0d;

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
                actions[actions.length - 2] = "+";
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
        double result = Double.parseDouble(actions[0]);

        for(int i = 1; i< actions.length; i++){
            if (actions[i].equals("+") || actions[i].equals("-") || actions[i].equals("÷") || actions[i].equals("×")) {
                result = one_action(actions[i], result, Double.parseDouble(actions[i+1]));
            }
        }

        return result;

    }
    private static String[] divide_into_parts(){
        String[] actions = new String[99];
        String temp = "";
        int element = 0;

        temp = "%s%s".formatted(temp, input.charAt(0));
        for (int i = 1; i<input.length(); i++){
            if (input.charAt(i)=='+' || input.charAt(i)== '-' || input.charAt(i)=='×' || input.charAt(i)=='÷'){
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
