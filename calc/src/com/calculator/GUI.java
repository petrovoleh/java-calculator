package com.calculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class numButton extends JButton implements ActionListener {
    Font ButtonsFont = new Font("Century Gothic", Font.BOLD, 30);
    Font Times = new Font("TimesRoman", Font.BOLD, 30);
    public numButton(String name, int x, int y) {
        setText(name);
        if (name != "⌫") setFont(ButtonsFont);
        else setFont(Times);

        setSize(80, 80);
        setLocation(x, y);
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("=")){
            System.out.print("ravno");
        }
        else if(e.getActionCommand().equals("+")){
            System.out.print("plus");
        }
        System.out.println(e.getActionCommand());
    }
}
public class GUI extends JFrame {
    Font TextFont = new Font("Arial", Font.BOLD, 30);

    public GUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);



        add(new numButton("⌫", 10, 110));
        add(new numButton("C", 110, 110));
        add(new numButton("%", 210, 110));
        add(new numButton("/", 310, 110));

        add(new numButton("7", 10, 210));
        add(new numButton("8", 110, 210));
        add(new numButton("9", 210, 210));
        add(new numButton("×", 310, 210));

        add(new numButton("4", 10, 310));
        add(new numButton("5", 110, 310));
        add(new numButton("6", 210, 310));
        add(new numButton("-", 310, 310));

        add(new numButton("1", 10, 410));
        add(new numButton("2", 110, 410));
        add(new numButton("3", 210, 410));
        add(new numButton("+", 310, 410));

        add(new numButton("±", 10, 510));
        add(new numButton("0", 110, 510));
        add(new numButton(".", 210, 510));
        add(new numButton("=", 310, 510));



        JLabel outputLabel = new JLabel("Some calculations");
        outputLabel.setForeground(Color.WHITE);
        outputLabel.setFont(TextFont);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setSize(380, 80);
        panel.setBackground(Color.BLACK);
        panel.setLocation(10, 10);
        panel.add(outputLabel);
        add(panel);


        setSize(420,640);
        setLayout(null);
        setVisible(true);
    }

}


