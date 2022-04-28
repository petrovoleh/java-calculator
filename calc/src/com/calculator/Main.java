package com.calculator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int first= Integer.parseInt(myObj.nextLine());
        String action= myObj.nextLine();
        int second= Integer.parseInt(myObj.nextLine());
        int result=Logic.get_result(first, second, action);
        System.out.println(result);
    }
}

