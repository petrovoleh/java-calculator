package com.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//ENTRY FIELD CLASS
class Panel extends JPanel{
    Font TextFont = new Font("TimesRoman", Font.BOLD, 30);
    public Panel(JLabel entry_field){
        entry_field.setForeground(Color.WHITE);
        entry_field.setFont(TextFont);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        setSize(355, 80);
        setBackground(Color.BLACK);
        setLocation(10, 10);
        add(entry_field);
    }
}

//BUTTONS CLASS
class numButton extends JButton{
    Font Times = new Font("TimesRoman", Font.BOLD, 30);
    public numButton(String name, int x, int y, GUI parent) {
        setText(name);
        setFont(Times);

        setSize(80, 60);
        setLocation(x, y);
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        addActionListener(parent);
    }
}

//GUI (ALL BUTTONS WRITTEN HERE)
public class GUI extends JFrame implements ActionListener, KeyListener {
    JLabel entry_field = new JLabel("0");

    public GUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setSize(385,570);

        add(new numButton("%", 100, 110, this));
        add(new numButton("C", 190, 110,this));
        add(new numButton("⌫", 280, 110, this));

        add(new numButton("(", 10, 180, this));
        add(new numButton(")", 100, 180, this));
        add(new numButton("x²", 190, 180,this));
        add(new numButton("÷", 280, 180, this));

        add(new numButton("7", 10, 250, this));
        add(new numButton("8", 100, 250, this));
        add(new numButton("9", 190, 250, this));
        add(new numButton("×", 280, 250, this));

        add(new numButton("4", 10, 320, this));
        add(new numButton("5", 100, 320, this));
        add(new numButton("6", 190, 320, this));
        add(new numButton("-", 280, 320, this));

        add(new numButton("1", 10, 390, this));
        add(new numButton("2", 100, 390, this));
        add(new numButton("3", 190, 390, this));
        add(new numButton("+", 280, 390, this));

        add(new numButton("±", 10, 460, this));
        add(new numButton("0", 100, 460, this));
        add(new numButton(".", 190, 460, this));
        add(new numButton("=", 280, 460, this));

        add(new Panel(entry_field));

        setLayout(null);
        setVisible(true);
        setResizable(false);
        addKeyListener(this);
        setFocusable(true);
    }
    //ACTION PERFORMED(actions on pressing all buttons)
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "⌫" -> Logic.backspace();
            case "C" -> Logic.input = "0";
            case "±" -> Logic.change_sign();
            case "%" -> Logic.percent();
            case "×","÷" -> Logic.multiply_divide(e.getActionCommand());
            case "-" -> Logic.minus();
            case "+" -> Logic.plus();
            case "x²" -> Logic.degree();
            case "=" ->  {entry_field.setText(Logic.get_result()); return;}
            default -> Logic.enter_numbers(e.getActionCommand());
        }
        entry_field.setText(Logic.input);
        requestFocusInWindow();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        String key = String.valueOf(e.getKeyChar());
        System.out.println("Key Typed");
        System.out.println(keyCode);
        switch (keyCode) {
            case 8 -> Logic.backspace();
            case 67 -> Logic.input = "0";
            default -> {
                if((48 <=keyCode && keyCode<=57) || (96 <=keyCode && keyCode<=105))
                    Logic.enter_numbers(key);
            }
        }

        entry_field.setText(Logic.input);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}



