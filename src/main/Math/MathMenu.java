package main.Math;

import javax.swing.*;

public class MathMenu extends JTabbedPane {
    public MathMenu(){
        addTab("Quadratic Equation",new QuadraticEquation());
        addTab("Complex Number",new ComplexNumber());
        addTab("Find Number",new FindNumber());
    }
}
