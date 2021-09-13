package test;

import main.ToDoList.Loadable;
import main.ToDoList.ToDoList;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LoadableTest {

    @Test
    public void load() throws IOException {
        Loadable toDoList = new ToDoList();
        assertTrue(toDoList.load("eventData.txt"));
    }


}