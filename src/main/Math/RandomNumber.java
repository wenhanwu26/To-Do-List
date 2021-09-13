package main.Math;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RandomNumber extends JPanel implements Compute{
    private JPanel organizer, left;
    private JLabel information;
    private JTextArea result;
    private JTextField firstNum, secondNum, number;
    private JButton compute;

    public RandomNumber() {
        organizer = new JPanel();

        firstNum = new JTextField();
        secondNum = new JTextField();
        number = new JTextField();
        JPanel input = new JPanel();
        input.setBorder(new TitledBorder(new EtchedBorder(), "User Input"));
        input.setLayout(new GridLayout(3, 2));
        input.add(new JLabel("First: "));
        input.add(firstNum);
        input.add(new JLabel("Second: "));
        input.add(secondNum);
        input.add(new JLabel("Number: "));
        input.add(number);
        left = new JPanel();
        left.add(input);

        result = new JTextArea(20, 40); //change to scroll pane
        result.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(result);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        result.setLineWrap(true);
        result.setWrapStyleWord(true);

        compute = new JButton("Generate");
        compute.addActionListener(event -> compute());

        information = new JLabel("Generate 'Number' random number between 'First' to 'Second' inclusive ");
        organizer.setLayout(new BorderLayout());
        organizer.add(information, BorderLayout.PAGE_START);
        organizer.add(left, BorderLayout.LINE_START);
        organizer.add(scrollPane, BorderLayout.LINE_END);
        organizer.add(compute, BorderLayout.PAGE_END);
        add(organizer);
    }

    public void compute() {
        int num = 0;
        int firstNum = 0;
        int SecondNum = 0;
        try {
            num = Integer.parseInt(number.getText());
            firstNum = Integer.parseInt(this.firstNum.getText());
            SecondNum = Integer.parseInt(secondNum.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, " Invalid Argument ");
            return;
        }
        String result = "";
        int max = firstNum >= SecondNum ? firstNum : SecondNum;
        int min = firstNum < SecondNum ? firstNum : SecondNum;
        for (int i = 1; i <= num; i++) {
            result += (int)(Math.random() * (max - min + 1) + min) + ", ";
        }
        this.result.setText(result);
    }
}
