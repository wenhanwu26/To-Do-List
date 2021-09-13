package main.Math;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class QuadraticEquation extends JPanel implements Compute{

    private JTextField displayAnswer;
    private JTextField displayEquation;

    public QuadraticEquation() {
        JPanel organizer = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel textField = new JPanel();
        JPanel answerPanel = new JPanel();
        JPanel equationPanel = new JPanel();
        JLabel answer = new JLabel("Answer: ");
        JLabel equation = new JLabel("Equation: ");


        displayAnswer = new JTextField(20);
        displayAnswer.setEditable(false);
        displayEquation = new JTextField(20);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//        complex = new JButton("Complex number");
//        quadratic = new JButton("Quadratic equation");
//        quadratic.addActionListener(event->quadraticEquation ());
        JButton compute = new JButton("Compute Root");
        compute.addActionListener(event -> compute());
        displayEquation.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    compute();
                }
            }
        });
        displayEquation.setFocusable(true);
        buttonPanel.add(compute);
        equationPanel.add(equation, BorderLayout.EAST);
        equationPanel.add(displayEquation, BorderLayout.EAST);
        answerPanel.add(answer, BorderLayout.WEST);
        answerPanel.add(displayAnswer, BorderLayout.WEST);
        textField.setLayout(new BoxLayout(textField, BoxLayout.Y_AXIS));
        textField.add(equationPanel);
        textField.add(answerPanel);
        organizer.add(textField, BorderLayout.WEST);
        organizer.add(buttonPanel, BorderLayout.EAST);
        add(organizer);
    }

    public void compute() {
        displayAnswer.setText(quadraticEquation(displayEquation.getText()));
    }

    //EFFECTS:  get the userinput, and compute the number of roots and the roots accordingly.
    public String quadraticEquation(String input) { //dont work for number larger than 9 //check how to separate by comma
        String[] number = input.split(",");
        // System.out.println(Arrays.toString(number));
        int numberOfRoots; //number of real roots
        double a, b, c, root1 = 0, root2 = 0;
        try {
            a = Double.parseDouble(number[0]);
            b = Double.parseDouble(number[1]);
            c = Double.parseDouble(number[2]);
            if (a == 0 || number.length > 3) {
                JOptionPane.showMessageDialog(null, " Invalid Argument ");
                return "";
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, " Invalid Argument ");
            return "";
        }

        if (Math.pow(b, 2) < 4 * a * c) {
            numberOfRoots = 0;
        } else if (Math.pow(b, 2) == 4 * a * c) {
            numberOfRoots = 1;
            root1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
            root1 = Math.round(root1 * 100.0) / 100.0;//round to 2 decimal place
            root2 = root1;
        } else {
            numberOfRoots = 2;
            root1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
            root1 = Math.round(root1 * 100.0) / 100.0; //round to 2 decimal place
            root2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
            root2 = Math.round(root2 * 100.0) / 100.0;//round to 2 decimal place
        }

        String answer = "Root number: " + numberOfRoots;
        if (numberOfRoots == 2) {
            answer += " First root:" + root1 + " Second root:" + root2;
        } else if (numberOfRoots == 1) {
            answer += " Root is " + root1;
        } else {
            answer += " No root";
        }
        return answer;
    }
}
