package com.calculator;
import javax.swing.*;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        JFrame frame = new GUI();

        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();

        int result=Logic.get_result(input);
        System.out.println(result);
    }
}

