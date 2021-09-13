package main.ToDoList;

import main.ToDoList.PopUp.PopUp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

public abstract class EventInformation extends JPanel {
    private JTable eventInformation;
    private JButton add, restore, save;
    private ArrayList<Event> eventData;
    private ToDoList toDoList;
    public EventInformation(ArrayList<Event> eventData, ToDoList toDoList) {
        this.eventData = eventData;
        this.toDoList = toDoList;
        Vector columnName = new Vector();
        columnName.add("Start Date");
        columnName.add("End Date");
        columnName.add("Title");
        columnName.add("Relevance");
        columnName.add("Urgency");
        columnName.add("Status");
        columnName.add("Change to");
        columnName.add("MODIFY");
        columnName.add("DELETE");

        eventInformation = new JTable(getInformation(eventData), columnName) {
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return columnIndex == 6 || columnIndex == 7 || columnIndex == 8;
            }
        };
        int dateWidth = 110;
        eventInformation.getColumnModel().getColumn(0).setPreferredWidth(dateWidth);
        eventInformation.getColumnModel().getColumn(1).setPreferredWidth(dateWidth);
        ButtonColumn statusColumn = new ButtonColumn(eventInformation, change(), 6);
        ButtonColumn modifyColumn = new ButtonColumn(eventInformation, modify(), 7);
        ButtonColumn deleteColumn = new ButtonColumn(eventInformation, delete(), 8);
        JScrollPane scrollPane = new JScrollPane(eventInformation);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel buttonOrganizer = new JPanel();
        add = new JButton("ADD");
        add.addActionListener(e -> add(toDoList));
        restore = new JButton("RESTORE");
        save = new JButton("SAVE");
        save.addActionListener(e-> {
            try {
                toDoList.saveEventToFile();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
        });
        buttonOrganizer.add(add);
        buttonOrganizer.add(restore);
        buttonOrganizer.add(save);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonOrganizer, BorderLayout.PAGE_END);
    }


    public Vector<Vector<String>> getInformation(ArrayList<Event> eventData) {
        //get the already filtered data (arraylist) and then (to stream) filter data again based on finished/ in progress use instanceof finished /in progress

//        System.out.println(this.getClass());
//        System.out.println(eventData.toString());

        Vector<Vector<String>> data = new Vector<Vector<String>>();
        //get from arraylist
       // System.out.println(eventData.toString());
        for (Event e : eventData) {
            Vector<String> information = new Vector<String>();
            //System.out.println(e.toString());
            information.add(e.getStartDate() + "");
            information.add(e.getEndDate() + "");
            information.add(e.getTitle() + "");
            information.add(e.getRelevance() + "");
            information.add(e.getUrgency() + "");
            information.add(e.getStatus() + "");
            if (e.getStatus().equals(Status.IN_PROGRESS)) {
                information.add(Status.FINISHED + "");
            } else {
                information.add(Status.IN_PROGRESS + "");
            }
            information.add("MODIFY");
            information.add("DELETE");
            data.add(information);
        }
//        System.out.println(this.getClass());
//        System.out.println(data.toString());
        return data;
    }

    public Action change() {
        return new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                //use the row number to find which event should change status
                int modelRow = Integer.valueOf(e.getActionCommand());
                toDoList.changeStatusByID1(eventData.get(modelRow).getID());
                //((DefaultTableModel) table.getModel()).removeRow(modelRow);
            }
        };
    }

    public Action modify() {
        return new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                //use the row number to find which event should change status
                int modelRow = Integer.valueOf(e.getActionCommand());
                PopUp popUp = new PopUp(eventData.get(modelRow),toDoList);
               // ((DefaultTableModel) table.getModel()).removeRow(modelRow);
            }
        };
    }

    public Action delete() {
        return new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable) e.getSource();
                //use the row number to find which event should change status
                int modelRow = Integer.valueOf(e.getActionCommand());
                toDoList.removeEventByID(eventData.get(modelRow).getID());
               // ((DefaultTableModel) table.getModel()).removeRow(modelRow);
            }
        };
    }

    public void add(ToDoList toDoList) {
        PopUp popUp = new PopUp(toDoList);
    }

}
