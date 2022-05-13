package com.calculator;


public class Logic {
    public static String input = "0";
    public static boolean divide_by_zero = false;
    public static boolean wrong_parentheses = false;
    public static final String signs = "+-÷×";

    private static double one_action(String action, double first, double second) {
        switch (action) {
            case "+" -> {return first + second;}
            case "-" -> {return first - second;}
            case "×" -> {return first * second;}
            case "÷" -> {return first / second;}
            case "x²" -> {return first * first;}
            default -> {return first;}
        }
    }

    public static void backspace(){
        input = input.substring(0, input.length() - 1);
        if(input.length()<1)
            input = "0";
    }

    private static boolean is_it_sign(int place){
        if (place == -1)
            return signs.contains(input.substring(input.length() - 1));
        return signs.contains(input.substring(place,place+1));
    }

    private static boolean is_action_contains(String symbols, int where){
        String[] actions = divide_into_parts();
        if (where == -1)
            where = actions.length-1;
        for (int i = 0; i<symbols.length()-1; i++) {
            if (actions[where].contains(symbols.substring(i,i+1))){
                return true;
            }
        }
        return false;
    }

    public static void dot(){
        if(!is_it_sign(-1) && !is_action_contains(".()",-1))
            input += (".");
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
            if (!is_it_sign(-1)) {
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

    public static void multiply_divide_plus(String command){
        if (is_it_sign(-1))
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
        if (!is_it_sign(-1)) {
            String[] actions = divide_into_parts();
            int len = actions.length;
            actions[len-1] = String.valueOf(one_action("x²", Double.parseDouble(actions[len-1]), 0));
            actions_to_input(actions);
        }
    }

    private static int[] priority(String[] actions){
        int len= actions.length;
        int[] prior = new int[len];
        for(int i = 0; i<len; i++){
            while(actions[i].contains("(")){
                int j =i;
                while (!actions[j].contains(")")) {
                    prior[j + 1] += 2;
                    j+=2;
                }
                actions[i] = actions[i].substring(1);
                actions[j] =(actions[j].substring(0, actions[j].length() - 1));
            }
            if(is_action_contains("×÷",i)){
                prior[i] +=2;
            }
            else if (is_action_contains("-+",i)){
                prior[i] +=1;
            }
        }
        return prior;
    }



    public static String[] removeElement(String[] arr, int el){
        int len= arr.length;
        String[] result_arr = new String[len-2];
        System.arraycopy(arr, 0, result_arr, 0, el + 1);
        if (len-3>el)
            System.arraycopy(arr, el + 1 + 2, result_arr, el + 1, len - 2 - (el + 1));
        return result_arr;
    }

    public static int[] removeElementInt(int[] arr, int el){
        int len= arr.length;
        int[] result_arr = new int[len-2];
        System.arraycopy(arr, 0, result_arr, 0, el + 1);
        if (len-3>el)
            System.arraycopy(arr, el + 1 + 2, result_arr, el + 1, len - 2 - (el + 1));
        return result_arr;
    }


    public static void get_result() {
        if (are_parentheses_wrong()){
            wrong_parentheses = true;
            return;
        }

        if (is_it_sign(-1))
            input = input.substring(0, input.length() - 1);
        String[] actions = divide_into_parts();
        int len = actions.length;
        int[] prior = priority(actions);
        while(len>1) {
            int highest = 0;
            for (int i = len - 1; i >= 0; i--) {
                if (prior[i] >= prior[highest])
                    highest = i;
            }
            actions[highest - 1] = String.valueOf(one_action(actions[highest], Double.parseDouble(actions[highest - 1]), Double.parseDouble(actions[highest + 1])));

            actions = removeElement(actions, highest - 1);
            prior = removeElementInt(prior, highest - 1);
            len = actions.length;
        }
        double result = Double.parseDouble(actions[0]);
        if (result % 1 == 0)
            input = String.valueOf(Math.round(result));
        else
            input = String.valueOf(result);
        if (Double.isInfinite(result)) {
            input = "0";
            divide_by_zero= true;
        }
    }

    public static void parentheses(String bracket){
        if(input.charAt(input.length()-1) != '.') {
            if (bracket.equals(")")) {
                String[] actions = divide_into_parts();
                if (!is_it_sign(-1) && !is_action_contains("(",-1)) {
                    if (are_parentheses_wrong())
                        input += (bracket);
                }
            } else {
                if (input.equals("0"))
                    input = "";
                input += (bracket);
            }
        }
    }

    public static String output(){
        if (divide_by_zero) {
            divide_by_zero = false;
            return "Cannot divide by zero";
        }
        if (wrong_parentheses) {
            wrong_parentheses = false;
            return "Parentheses are wrong";
        }
        return input;
    }

    private static boolean are_parentheses_wrong(){
        int brackets = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                brackets++;
            } else if (input.charAt(i) == ')') {
                brackets--;
            }
        }
        return brackets > 0;
    }

    private static String[] divide_into_parts(){
        String[] actions = new String[99];
        String temp = "";
        int element = 0;

        temp = "%s%s".formatted(temp, input.charAt(0));
        for (int i = 1; i<input.length(); i++){
            if (is_it_sign(i) && !is_it_sign(i-1)){
                actions[element] = temp;
                actions[element+1] = String.valueOf(input.charAt(i));
                element+=2;
                temp="";
            }
            else if(input.charAt(i)=='(' && !is_it_sign(i-1) && input.charAt(i-1)!='('){
                actions[element] = temp;
                actions[element+1] = "×";
                element+=2;
                temp="(";
            }
            else if(input.charAt(i-1)==')' && !is_it_sign(i) && input.charAt(i)!=')'){
                actions[element] = temp;
                actions[element+1] = "×";
                element+=2;
                temp=String.valueOf(input.charAt(i));
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