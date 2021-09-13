package test;

import main.ToDoList.Event;
import main.ToDoList.Saveable;
import main.ToDoList.ToDoList;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class SaveableTest {

    @Test
    public void save() throws FileNotFoundException, UnsupportedEncodingException {
        ToDoList toDoList = new ToDoList();
        toDoList.saveEventToFile();
        File f = new File("eventData.txt");
        assertTrue(f.exists()&&!f.isDirectory());
    }
}