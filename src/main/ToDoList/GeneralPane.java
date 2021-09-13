package main.ToDoList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GeneralPane extends JTabbedPane {
    private int indexSelected;
    private ArrayList<Event> data;
    private Finished finished;
    private InProgress inProgress;

    public GeneralPane(ArrayList<Event> eventData, ToDoList toDoList) {
        data = eventData;
        finished = new Finished(getInformation(Status.FINISHED), toDoList);
        inProgress = new InProgress(getInformation(Status.IN_PROGRESS), toDoList);
        add("In Progress", inProgress);
        add("Finished", finished);
        addChangeListener(e -> changeTab(this, toDoList)); // to refilter the data
    }

    public void changeTab(GeneralPane pane, ToDoList toDoList) {
        InProgress inProgress = new InProgress(getInformation(Status.IN_PROGRESS), toDoList);
        Finished finished = new Finished(getInformation(Status.FINISHED), toDoList);
        pane.setComponentAt(0, inProgress);
        pane.setComponentAt(1, finished);

    }

    public ArrayList<Event> getInformation(Status status) {
        //receive  alreay filter Arraylist as parameter to private field
        // . use instance of home..to find which to filter then return the arraylist
        Stream<Event> dataStream = data.stream();
        if (status.equals(Status.IN_PROGRESS)) {
            List<Event> eventData = dataStream.filter(e -> e.getStatus().equals(Status.IN_PROGRESS)).collect(Collectors.toList());
//            System.out.println(Status.IN_PROGRESS);
//            System.out.println(this.getClass());
//            System.out.println(eventData.toString());
            return new ArrayList<Event>(eventData);
        } else {
            List<Event> eventData = dataStream.filter(e -> e.getStatus().equals(Status.FINISHED)).collect(Collectors.toList());
//            System.out.println(Status.FINISHED);
//            System.out.println(this.getClass());
//            System.out.println(eventData.toString());
            return new ArrayList<Event>(eventData);
        }
    }

    public int getindexSelected() {
        return this.indexSelected;
    }
}
