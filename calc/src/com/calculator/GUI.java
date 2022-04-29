package com.calculator;
import javax.swing.*;
import java.awt.Font;

class numButton extends JButton{
    Font BigFontTR = new Font("TimesRoman", Font.BOLD, 30);
    public numButton(String name, int x, int y) {
        setText(name);
        setSize(80, 80);
        setLocation(x, y);
        setFont(BigFontTR);
    }
}
class Layout extends JFrame {
    public Layout() {
    setTitle("Calculator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(420,640);

    }

}
public class GUI {
    public static void draw_gui() {
        JFrame frame = new Layout();



        frame.add(new numButton("<", 10, 110));
        frame.add(new numButton("C", 110, 110));
        frame.add(new numButton("%", 210, 110));
        frame.add(new numButton("/", 310, 110));

        frame.add(new numButton("7", 10, 210));
        frame.add(new numButton("8", 110, 210));
        frame.add(new numButton("9", 210, 210));
        frame.add(new numButton("X", 310, 210));

        frame.add(new numButton("4", 10, 310));
        frame.add(new numButton("5", 110, 310));
        frame.add(new numButton("6", 210, 310));
        frame.add(new numButton("-", 310, 310));

        frame.add(new numButton("1", 10, 410));
        frame.add(new numButton("2", 110, 410));
        frame.add(new numButton("3", 210, 410));
        frame.add(new numButton("+", 310, 410));

        frame.add(new numButton("+/-", 10, 510));
        frame.add(new numButton("0", 110, 510));
        frame.add(new numButton(".", 210, 510));
        frame.add(new numButton("=", 310, 510));

        frame.setLayout(null);
        frame.setVisible(true);
    }
}

