package main.ui;

import javax.swing.*;

public class Information extends JPanel {
    private JLabel simpleCalculation, QuadraticEquation,CalculatorPanel;
    public Information (){
        String s = "<html> Simple Calculation<br/>The simple calculation tab provide the functionality of adding, subtracting, multiplying, dividing, and " +
                "modulo</html><br/>";
        simpleCalculation = new JLabel(s);
        add(simpleCalculation);

    }
}
