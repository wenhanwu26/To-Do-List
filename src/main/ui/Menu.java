package main.ui;

import main.Math.MathMenu;
import main.ToDoList.ToDoList;
import main.ui.Information;
import main.Text.TextStatistics;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

public class Menu extends JTabbedPane  {
    public Menu()  {
       // addTab("Information",new Information());
        addTab("ToDoList",new ToDoList());
        //addTab("Math Menu",new MathMenu());
      //  addTab("Text Statistics", new TextStatistics());
        setPreferredSize(new Dimension(1000,500));

    }
}
