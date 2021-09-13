package main.ToDoList;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Comparable<Event> {
    private String startDate;
    private String endDate;
    private String title;
    private String description;
    private Relevance relevance;
    private int urgency;
    private Status status;
    private int ID;

    public Event(int ID) {
        startDate = dateFormatting(LocalDateTime.now());
        endDate = dateFormatting(LocalDateTime.now());
        title = "";
        description = "";
        relevance = Relevance.OTHERS;
        urgency = 0;
        status = Status.IN_PROGRESS;
        this.ID =  ID;
    }

    public Event(LocalDateTime startDate, LocalDateTime endDate,String title, String description, int urgency, Relevance relevance, Status status, int ID) {
        this.startDate = dateFormatting(startDate);
        this.endDate = dateFormatting(endDate);
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.relevance = relevance;
        this.status = status;
        this.ID = ID;
    }

    private String dateFormatting(LocalDateTime date) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, MMM dd yyyy HH:mm");
        return date.format(myFormatObj);
    }

    public int compareTo(Event o) {
        return startDate.compareTo(o.startDate);
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public Relevance getRelevance() {
        return relevance;
    }

    public int getUrgency() {
        return urgency;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Event{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", relevance=" + relevance +
                ", urgency=" + urgency +
                ", status=" + status +
                ", ID=" + ID +
                '}';
    }
}
