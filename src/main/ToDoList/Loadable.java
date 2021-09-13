package main.ToDoList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface Loadable {
    default boolean load(String file) throws IOException {
        ArrayList<String> events = getDataFromFile(file);
        for(String str:events){
            System.out.println(str);
        }
        return true;
    }
    default ArrayList<String> getDataFromFile(String file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file));
        return new ArrayList<String>(lines);
    }
    default ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<String>(Arrays.asList(splits));
    }

}
