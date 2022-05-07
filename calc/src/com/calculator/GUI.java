package com.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
public class GUI extends JFrame implements ActionListener {
    Font TextFont = new Font("TimesRoman", Font.BOLD, 30);
    JLabel outputLabel = new JLabel("0");

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

        outputLabel.setForeground(Color.WHITE);
        outputLabel.setFont(TextFont);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setSize(355, 80);
        panel.setBackground(Color.BLACK);
        panel.setLocation(10, 10);
        panel.add(outputLabel);
        add(panel);

        setLayout(null);
        setVisible(true);
        setResizable(false);
    }
    //ACTION PERFORMED(actions on pressing all buttons)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("⌫")) {
            Logic.input = Logic.input.substring(0, Logic.input.length() - 1);
        } else if (e.getActionCommand().equals("C")) {
            Logic.input = "0";
        } else if (e.getActionCommand().equals("±")) {
            Logic.change_sign();
        } else if (e.getActionCommand().equals("%")) {
            Logic.percent();
        } else if (e.getActionCommand().equals("×") || e.getActionCommand().equals("÷")) {
            Logic.multiply_divide(e.getActionCommand());
        } else if (e.getActionCommand().equals("-")){
            Logic.minus();
        } else if (e.getActionCommand().equals("+")) {
            Logic.plus();
        } else if (e.getActionCommand().equals("x²")) {
            Logic.degree();
        } else if (e.getActionCommand().equals("=")) {
            outputLabel.setText(Logic.get_result());
            return;
        } else {
            Logic.enter_numbers(e.getActionCommand());
        }
        outputLabel.setText(Logic.input);
    }
}



