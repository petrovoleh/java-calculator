package com.calculator;
import javax.swing.*;
import java.awt.*;

class numButton extends JButton{
    Font ButtonsFont = new Font("TimesRoman", Font.BOLD, 30);
    public numButton(String name) {
        setText(name);
        setFont(ButtonsFont);
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
    }
}
class Layout extends JFrame {
    Font TextFont = new Font("TimesRoman", Font.BOLD, 30);

    public Layout() {
    setTitle("Calculator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.BLACK);


    JPanel grid = new JPanel(new GridBagLayout());

    JLabel output = new JLabel("Some calculations");
    output.setForeground(Color.WHITE);
    output.setFont(TextFont);
    GridBagConstraints constraints = new GridBagConstraints();



    constraints.ipady     = 50;

    constraints.gridwidth = 4;
    grid.add(output,constraints);

    constraints.ipadx     = 50;
    constraints.gridy = 1;
    constraints.gridwidth = 1;

    grid.add(new numButton("⌫"),constraints);
    grid.add(new numButton("C"),constraints);
    grid.add(new numButton("%"),constraints);
    grid.add(new numButton("/"),constraints);

    constraints.gridy = 2;

    grid.add(new numButton("7"),constraints);
    grid.add(new numButton("8"),constraints);
    grid.add(new numButton("9"),constraints);
    grid.add(new numButton("×"),constraints);

    constraints.gridy = 3;

    grid.add(new numButton("4"),constraints);
    grid.add(new numButton("5"),constraints);
    grid.add(new numButton("6"),constraints);
    grid.add(new numButton("-"),constraints);

    constraints.gridy = 4;

    grid.add(new numButton("1"),constraints);
    grid.add(new numButton("2"),constraints);
    grid.add(new numButton("3"),constraints);
    grid.add(new numButton("+"),constraints);

    constraints.gridy = 5;

    grid.add(new numButton("±"),constraints);
    grid.add(new numButton("0"),constraints);
    grid.add(new numButton("."),constraints);
    grid.add(new numButton("="),constraints);



    pack();
    setSize(420,640);

    setLayout(new FlowLayout(FlowLayout.RIGHT));
    add(grid);

    setVisible(true);

    }

}
public class GUI {
    public static void draw_gui() {
        JFrame frame = new Layout();




    }
}

