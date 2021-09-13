package main.ToDoList.PopUp;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;

import main.ToDoList.Event;
import main.ToDoList.Relevance;
import main.ToDoList.Status;
import main.ToDoList.ToDoList;
import org.jdesktop.swingx.JXDatePicker;

public class PopUpMenu extends JPanel {
    private JTextField title;
    private JTextArea description;
    private JXDatePicker startDate, endDate;
    private JComboBox<Relevance> relevanceJComboBox;
    private JComboBox<Integer> urgencyJComboBox;
    private JButton exit, add;
    private JSpinner startTime, endTime;
    private ToDoList toDoList;
    private PopUp popUp;

    public PopUpMenu(ToDoList toDoList, PopUp popUp, boolean modifyMode) {
        this.toDoList = toDoList;
        this.popUp = popUp;
        JPanel organizer = new JPanel();
        organizer.setLayout(new BoxLayout(organizer, BoxLayout.Y_AXIS));
        JPanel top = new JPanel();
        top.setLayout(new GridLayout(8, 2));
        top.add(new JLabel("Title: "));
        title = new JTextField();
        top.add(title);
        top.add(new JLabel("Start Date: "));
        startDate = new JXDatePicker();
        top.add(startDate);
        top.add(new JLabel("Start Time: "));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        calendar.set(Calendar.MINUTE, 1440);
        calendar.set(Calendar.SECOND, 0);
        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(calendar.getTime());
        startTime = new JSpinner(model);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(startTime, "HH:mm");
        startTime.setEditor(timeEditor);
        top.add(startTime);
        top.add(new JLabel("End Date: "));
        endDate = new JXDatePicker();
        top.add(endDate);
        top.add(new JLabel("End Time: "));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 24);
        calendar1.set(Calendar.MINUTE, 1439);
        calendar1.set(Calendar.SECOND, 0);
        SpinnerDateModel model1 = new SpinnerDateModel();
        model1.setValue(calendar1.getTime());
        endTime = new JSpinner(model1);
        timeEditor = new JSpinner.DateEditor(endTime, "HH:mm");
        endTime.setEditor(timeEditor);
        top.add(endTime);
        top.add(new JLabel("Relevance:"));
        Relevance[] relevance = {Relevance.SCHOOL, Relevance.HOME, Relevance.OTHERS};
        relevanceJComboBox = new JComboBox<Relevance>(relevance);
        top.add(relevanceJComboBox);
        top.add(new JLabel("Urgency:"));
        Integer[] urgency = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        urgencyJComboBox = new JComboBox<Integer>(urgency);
        top.add(urgencyJComboBox);
        top.add(new JLabel("Description: "));
        organizer.add(top);


        description = new JTextArea(10, 10);
        JScrollPane scrollPane = new JScrollPane(description);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        organizer.add(scrollPane);

        JPanel buttonOrganizer = new JPanel();
        buttonOrganizer.setLayout(new BorderLayout());

        if (modifyMode) {
            add = new JButton(("  FINISH  "));
            buttonOrganizer.add(add, BorderLayout.LINE_END);
        } else {
            exit = new JButton(" EXIT ");
            exit.addActionListener(e -> popUp.dispose());
            add = new JButton(("  ADD  "));
            add.addActionListener(e -> getInput(false));
            buttonOrganizer.add(exit, BorderLayout.LINE_END);
            buttonOrganizer.add(add, BorderLayout.CENTER);
        }

        organizer.add(buttonOrganizer);
        add(organizer);

    }

    public PopUpMenu(Event e, ToDoList toDoList, PopUp popUp) {
        this(toDoList, popUp, true);
        title.setText(e.getTitle());
        description.setText(e.getDescription());
        relevanceJComboBox.setSelectedItem(e.getRelevance());
        urgencyJComboBox.setSelectedItem(e.getUrgency());
        add.addActionListener(e1 -> getInput(e));
    }

    public void getInput(Event e) {
        toDoList.removeEventByID(e.getID());
        getInput(true);
        popUp.dispose();
    }

    public void getInput(boolean modifyMode) {
        Date date = (Date) this.startTime.getValue();
        LocalTime startTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalDate startDate;
        try {
            startDate = LocalDateTime.ofInstant(this.startDate.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
        } catch (NullPointerException e) {
            startDate = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();
        }
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

        date = (Date) this.endTime.getValue();
        LocalTime endTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalDate endDate;
        try {
            endDate = LocalDateTime.ofInstant(this.endDate.getDate().toInstant(), ZoneId.systemDefault()).toLocalDate();
        } catch (NullPointerException e) {
            endDate = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();
        }
        LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

        String description = this.description.getText();
        String title = this.title.getText();
        int urgency = (int) urgencyJComboBox.getSelectedItem();
        Relevance relevance = (Relevance) relevanceJComboBox.getSelectedItem();
        //System.out.println(relevance);
        Status status = LocalDateTime.now().isBefore(endDateTime) ? Status.IN_PROGRESS : Status.FINISHED;
        Event newEvent = new Event(startDateTime, endDateTime, title, description, urgency, relevance, status, toDoList.getID());
//        System.out.println(newEvent.getTitle());
//        System.out.println(newEvent.getStartDate());
//        System.out.println(newEvent.getEndDate());
//        System.out.println(newEvent.getStatus());
//        System.out.println(newEvent.getID());
        toDoList.addEvent(newEvent);
        toDoList.resetTab();
        if (modifyMode) {
            JOptionPane.showMessageDialog(this, "Event changes successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Event adds successfully!");
        }


    }
}
