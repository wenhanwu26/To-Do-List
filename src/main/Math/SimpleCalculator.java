package main.Math;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;


public class SimpleCalculator extends JPanel{
    private JPanel organizer, buttonPanel, textField, answerPanel, equationPanel;
    private JButton compute;
    private JTextField displayAnswer, displayEquation;
    private JLabel answer, equation;

    public SimpleCalculator() {
        organizer = new JPanel();
        buttonPanel = new JPanel();
        textField = new JPanel();
        answerPanel = new JPanel();
        equationPanel = new JPanel();
        answer = new JLabel("Answer: ");
        equation = new JLabel("Equation: ");
        displayAnswer = new JTextField(20);
        displayAnswer.setEditable(false);
        displayEquation = new JTextField(20);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        compute = new JButton("Simple calculation");
        compute.addActionListener(event -> simpleCalculation());
        displayEquation.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    simpleCalculation();
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

    public void simpleCalculation() {
        String input = displayEquation.getText();
        String[] number = input.split("(?<=[-+*/])|(?=[-+*/])"); //internet
        // System.out.println(Arrays.toString(number));
        ArrayList<String> num = new ArrayList<String>(Arrays.asList(number));
        double value = 0;
        for (int i = 1; i < num.size()-1; i++) {
            if (num.get(i).equals("*") || num.get(i).equals("/") || num.get(i).equals("%")) {
                if (!isNumber(num.get(i - 1)) || !isNumber(num.get(i + 1))) {
                    JOptionPane.showMessageDialog(null, " Invalid Argument ");
                    return;
                } else {
                    value = Double.parseDouble(num.get(i - 1));
                    switch (num.get(i)) {
                        case "*":
                            value *= Double.parseDouble(num.get(i + 1));
                            break;
                        case "/":
                            value /= Double.parseDouble(num.get(i + 1));
                            break;
                        case "%":
                            value %= Double.parseDouble(num.get(i + 1));
                            break;
                    }
                    num.set(i-1, value + "");
                    num.remove(i);
                    num.remove(i + 1);
                    i--;
                }
            }
            if(num.size()==1){
                break;
            }
        }
        for (int i = 1; i < num.size()-1; i++) {
            if (num.get(i).equals("+") || num.get(i).equals("-")) {
                if (!isNumber(num.get(i - 1)) || !isNumber(num.get(i + 1))) {
                    System.out.println("here");
                    JOptionPane.showMessageDialog(null, " Invalid Argument ");
                    return;
                } else {
                    value = Double.parseDouble(num.get(i - 1));
                    switch (num.get(i)) {
                        case "+":
                            value += Double.parseDouble(num.get(i + 1));
                            break;
                        case "-":
                            value -= Double.parseDouble(num.get(i + 1));
                            break;
                    }
                    num.set(i-1, value + "");
                    num.remove(i);
                   // num.remove(i +);
                    i--;
                }
            }
            if(num.size()==1){
                break;
            }
        }

//        if (!isNumber(number[0])) {
//            JOptionPane.showMessageDialog(null, " Invalid Argument ");
//            return;
//        } else {
//            answer = Double.parseDouble(number[0]);
//        }

//        for (int i = 1; i < number.length; i += 2) {
//            if (!isOperator(number[i])) {
//                JOptionPane.showMessageDialog(null, " Invalid Argument ");
//                return;
//            } else if (!isNumber(number[i + 1])) {
//                JOptionPane.showMessageDialog(null, " Invalid Argument ");
//                return;
//            } else {
//                switch (number[i]) {
//                    case "+":
//                        answer += Double.parseDouble(number[i + 1]);
//                        break;
//                    case "-":
//                        answer -= Double.parseDouble(number[i + 1]);
//                        break;
//                    case "*":
//                        answer *= Double.parseDouble(number[i + 1]);
//                        break;
//                    case "/":
//                        answer /= Double.parseDouble(number[i + 1]);
//                        break;
//                    case "%":
//                        answer %= Double.parseDouble(number[i + 1]);
//                        break;
//                }
//            }
//        }
        displayAnswer.setText(value + "");
    }


    public static boolean isNumber(String number) {
        try {
            Double.parseDouble(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOperator(String operator) {
        return (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/") || operator.equals("%"));
    }
}
