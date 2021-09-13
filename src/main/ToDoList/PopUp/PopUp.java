package main.ToDoList.PopUp;

import main.ToDoList.Event;
import main.ToDoList.ToDoList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class PopUp extends JFrame {
    public PopUp(ToDoList toDoList) {
        setTitle("TOOLS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new PopUpMenu(toDoList,this,false));
      //  setSize(new Dimension(400,500));
        pack();
        setVisible(true);
    }
    public PopUp(Event e,ToDoList toDoList){
        setTitle("TOOLS");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new PopUpMenu(e,toDoList,this));
        //  setSize(new Dimension(400,500));
        pack();
        setVisible(true);
    }
}
