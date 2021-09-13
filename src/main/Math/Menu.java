package main.Math;

import main.ui.Information;
import main.Text.TextStatistics;

import javax.swing.*;

public class Menu extends JTabbedPane  {
    private Information information;
    private SimpleCalculator simpleCalculator;
    private QuadraticEquation quadraticEquation;
    private ComplexNumber complexNumber;
    private FindNumber findNumber;
    private TextStatistics textStatistics;
    public Menu(){
        information = new Information();
        simpleCalculator = new SimpleCalculator();
        quadraticEquation = new QuadraticEquation();
        complexNumber = new ComplexNumber();
        findNumber = new FindNumber();
        textStatistics = new TextStatistics();
        addTab("Information",information);
        //addTab("Simple Calculator",simpleCalculator);
        addTab("Quadratic Equation",quadraticEquation);
        addTab("Complex Number",complexNumber);
        addTab("Find Number",findNumber);
        addTab("Text Statistics", textStatistics);
    }
}
