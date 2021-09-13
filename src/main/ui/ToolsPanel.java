package main.ui;

import javax.swing.*;
import java.io.IOException;
import java.util.Date;

public class ToolsPanel {
    public static void main(String[] args)  {
        JFrame frame = new JFrame();
        frame.setTitle("TOOLS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Menu());
        //frame.setSize(1000,1000);
        frame.pack();
        frame.setVisible(true);


      //  String input = "2,3,1";

    }
}
