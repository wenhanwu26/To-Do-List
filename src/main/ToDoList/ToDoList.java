package main.ToDoList;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ToDoList extends JTabbedPane implements Saveable, Loadable {
    private int indexSelected;
    private ArrayList<Event> eventData;

    public ToDoList()  {
        try {
            eventData = readEventFromFile();
        }catch (IOException e){
            eventData = new ArrayList<>();
            try {
                saveEventToFile();
            }catch (Exception e1){

            }
        }
        add("All", new All(getInformation(Relevance.All), this));
        add("School", new School(getInformation(Relevance.SCHOOL), this));
        add("Home", new Home(getInformation(Relevance.HOME), this));
        add("Others", new Others(getInformation(Relevance.OTHERS), this));
        addChangeListener(e -> resetTab());
        indexSelected = this.getSelectedIndex();
    }

    public void resetTab() {
        // JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
        setComponentAt(0, new All(getInformation(Relevance.All), this));
        setComponentAt(1, new School(getInformation(Relevance.SCHOOL), this));
        setComponentAt(2, new Home(getInformation(Relevance.HOME), this));
        setComponentAt(3, new Others(getInformation(Relevance.OTHERS), this));
        //  indexSelected = tabbedPane.getSelectedIndex();
//        JOptionPane.showMessageDialog(null, "Selected Index: " + indexSelected);
        // Change tab eveytime should re-filter the data
    }

    public ArrayList<Event> getInformation(Relevance relevance) {
        //receive Arraylist as parameter to private field
        // (so every generalPane access same arraylist, convert to stream, then filter the data based on home/school.
        // . use instance of School..to find which to filter then return the arraylist

        Stream<Event> dataStream = eventData.stream();
        if (relevance.equals(Relevance.All)) {
            return new ArrayList<Event>(eventData);
        } else if (relevance.equals(Relevance.SCHOOL)) {
            List<Event> eventData = dataStream.filter(e -> e.getRelevance().equals(Relevance.SCHOOL)).collect(Collectors.toList());
            ArrayList<Event> e = new ArrayList<Event>(eventData);
            // System.out.println(e.toString());
            return new ArrayList<Event>(eventData);
        } else if (relevance.equals(Relevance.HOME)) {
            List<Event> eventData = dataStream.filter(e -> e.getRelevance().equals(Relevance.HOME)).collect(Collectors.toList());
            return new ArrayList<Event>(eventData);
        } else {
            List<Event> eventData = dataStream.filter(e -> e.getRelevance().equals(Relevance.OTHERS)).collect(Collectors.toList());
            return new ArrayList<Event>(eventData);
        }
    }

    public ArrayList<Event> readEventFromFile() throws IOException {
        load("eventData.txt");
        eventData = new ArrayList<Event>(); //change to read from file

        return eventData;
    }
    public void saveEventToFile() throws FileNotFoundException, UnsupportedEncodingException {
        save(eventData,"eventData.txt");
        JOptionPane.showMessageDialog(this,"Save Successfully!");
    }
    public void addEvent(Event e) {
        eventData.add(e);
        // System.out.println(eventData.size());
    }

    public int getID() {
        if (eventData.isEmpty()) {
            return 0;
        } else {
            int max = eventData.get(0).getID();
            for (Event e : eventData) {
                if (e.getID() > max) {
                    max = e.getID();
                }
            }
            return max + 1;
        }
    }

    public void removeEventByID(int ID) {
        for (Event e : eventData) {
            if (e.getID() == ID) {
                eventData.remove(e);
                resetTab();
                return;
            }
        }
    }

    public void changeStatusByID1(int ID) {
        for (Event e : eventData) {
            if (e.getID() == ID) {
                Status status = e.getStatus();
                status = status.equals(Status.IN_PROGRESS) ? Status.FINISHED : Status.IN_PROGRESS;
                // System.out.println(status);
                e.setStatus(status);
                resetTab();
                return;
            }
        }

    }

    public int getindexSelected() {
        return indexSelected;
    }
}
