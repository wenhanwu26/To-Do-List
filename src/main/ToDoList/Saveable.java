package main.ToDoList;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface Saveable{
    default void save(ArrayList<Event> eventData, String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(fileName,"UTF-8");
        for(Event e:eventData){
            writer.println(e.toString());
        }
        writer.close();
    }
}
