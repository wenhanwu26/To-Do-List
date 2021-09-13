package main.Math;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GCD extends JPanel implements Compute{
    private JPanel organizer, left;
    private JLabel information;
    private JTextField firstNum, secondNum;
    private JTextArea result;
    private JButton compute;

    public GCD() {
        organizer = new JPanel();

        firstNum = new JTextField();
        secondNum = new JTextField();
        JPanel input = new JPanel();
        input.setBorder(new TitledBorder(new EtchedBorder(), "User Input"));
        input.setLayout(new GridLayout(3, 2));
        input.add(new JLabel("First: "));
        input.add(firstNum);
        input.add(new JLabel("Second: "));
        input.add(secondNum);
        left = new JPanel();
        left.add(input);


        result = new JTextArea(5, 20); //change to scroll pane
        result.setEditable(false);

        compute = new JButton("Compute");
        compute.addActionListener(event -> compute());

        information = new JLabel("Compute the GCD between 'First' to 'Second' ");
        organizer.setLayout(new BorderLayout());
        organizer.add(information, BorderLayout.PAGE_START);
        organizer.add(left, BorderLayout.LINE_START);
        organizer.add(result, BorderLayout.LINE_END);
        organizer.add(compute, BorderLayout.PAGE_END);
        add(organizer);
    }

    public void compute() {
        int first = 0;
        int second = 0;
        try {
            first = Integer.parseInt(firstNum.getText());
            second = Integer.parseInt(secondNum.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, " Invalid Argument ");
            return;
        }
        String result = "";
        result += first > second ? euclidean(first, second) : euclidean(second, first) ;
        this.result.setText(result);
    }

    public int euclidean(int first, int second) {
        if(second==0){
            return first;
        }else{
            return euclidean(second,first%second);
        }
    }
}
