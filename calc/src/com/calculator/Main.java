package com.calculator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GUI.draw_gui();

        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();

        int result=Logic.get_result(input);

        System.out.println(result);
    }
}

