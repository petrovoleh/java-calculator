package com.calculator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Listener extends KeyAdapter implements ActionListener {
    boolean shift_pressed = false;
    private final JLabel field;

    public Listener(JLabel entry_field) {this.field = entry_field;}

    //PRESSING KEYS ON THE KEYBOARD
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String key = String.valueOf(e.getKeyChar());
        if (!shift_pressed) {
            switch (keyCode) {
                case 8 -> Logic.backspace();
                case 67, 27 -> Logic.input = "0";
                case 106 -> Logic.multiply_divide_plus("×");
                case 111, 47 -> Logic.multiply_divide_plus("÷");
                case 109, 45 -> Logic.minus();
                case 107 -> Logic.multiply_divide_plus("+");
                case 61 -> Logic.get_result();
                case 110, 44, 46 -> Logic.dot();
                case 16 -> shift_pressed = true;
                default -> {
                    if ((48 <= keyCode && keyCode <= 57) || (96 <= keyCode && keyCode <= 105))
                        Logic.enter_numbers(key);
                }
            }
        } else {
            switch (keyCode) {
                case 53 -> Logic.percent();
                case 56 -> Logic.multiply_divide_plus("×");
                case 61 -> Logic.multiply_divide_plus("+");
                case 54 -> Logic.degree();
                case 57, 48 -> Logic.parentheses(key);
                default -> {
                    if (96 <= keyCode && keyCode <= 105)
                        Logic.enter_numbers(key);
                }
            }
        }
        field.setText(Logic.output());
    }

    //RELEASED KEY SHIFT
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 16)
            shift_pressed = false;
    }

    //ACTION PERFORMED(pressing buttons on the screen)
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "⌫" -> Logic.backspace();
            case "C" -> Logic.input = "0";
            case "±" -> Logic.change_sign();
            case "%" -> Logic.percent();
            case "×","÷","+" -> Logic.multiply_divide_plus(e.getActionCommand());
            case "-" -> Logic.minus();
            case "x²" -> Logic.degree();
            case "." -> Logic.dot();
            case "=" ->  Logic.get_result();
            case "(",")" ->  Logic.parentheses(e.getActionCommand());
            default -> Logic.enter_numbers(e.getActionCommand());
        }
        field.setText(Logic.output());
        Main.gui.requestFocusInWindow();
    }
}
