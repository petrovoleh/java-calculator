package com.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class numButton extends JButton{
    Font Times = new Font("TimesRoman", Font.BOLD, 30);
    public numButton(String name, int x, int y, GUI parent) {
        setText(name);
        setFont(Times);

        setSize(80, 80);
        setLocation(x, y);
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        addActionListener(parent);
    }
}

public class GUI extends JFrame implements ActionListener {
    Font TextFont = new Font("TimesRoman", Font.BOLD, 30);
    JLabel outputLabel = new JLabel("0");

    public GUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setSize(420,640);

        add(new numButton("⌫", 10, 110, this));
        add(new numButton("C", 110, 110, this));
        add(new numButton("%", 210, 110,this));
        add(new numButton("÷", 310, 110, this));

        add(new numButton("7", 10, 210, this));
        add(new numButton("8", 110, 210, this));
        add(new numButton("9", 210, 210, this));
        add(new numButton("×", 310, 210, this));

        add(new numButton("4", 10, 310, this));
        add(new numButton("5", 110, 310, this));
        add(new numButton("6", 210, 310, this));
        add(new numButton("-", 310, 310, this));

        add(new numButton("1", 10, 410, this));
        add(new numButton("2", 110, 410, this));
        add(new numButton("3", 210, 410, this));
        add(new numButton("+", 310, 410, this));

        add(new numButton("±", 10, 510, this));
        add(new numButton("0", 110, 510, this));
        add(new numButton(".", 210, 510, this));
        add(new numButton("=", 310, 510, this));

        outputLabel.setForeground(Color.WHITE);
        outputLabel.setFont(TextFont);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setSize(380, 80);
        panel.setBackground(Color.BLACK);
        panel.setLocation(10, 10);
        panel.add(outputLabel);
        add(panel);

        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("⌫")) {
            Logic.input = Logic.input.substring(0, Logic.input.length() - 1);
        } else if (e.getActionCommand().equals("±")) {
            Logic.change_sign();
        } else if (e.getActionCommand().equals("C")) {
            Logic.input = "0";
        } else if (e.getActionCommand().equals("×") || e.getActionCommand().equals("÷")) {
            Logic.multiply_divide(e.getActionCommand());
        } else if(e.getActionCommand().equals("-")){
            Logic.minus();
        } else if(e.getActionCommand().equals("+")) {
            Logic.plus();
        } else if (e.getActionCommand().equals("=")) {
            outputLabel.setText(Logic.equals());
            return;
        } else {
            if (Logic.input.equals("0"))
                Logic.input = "";
            Logic.input += (e.getActionCommand());
        }
        outputLabel.setText(Logic.input);
    }
}



