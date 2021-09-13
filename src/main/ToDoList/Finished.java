package main.ToDoList;

import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Stream;

public class Finished extends EventInformation{
    public Finished(ArrayList<Event> eventData, ToDoList toDoList) {
        super(eventData,toDoList);
    }

    // get the filtered data (already filter by school, home, other...)


//    @Override
//    public Vector<Vector<String>> getInformation() {
//        Vector<Vector<String>> information= super.getInformation();
//        Stream<Vector<String>> streamInformation = information.stream();
//
//    }
}
