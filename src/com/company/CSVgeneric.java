package com.company;

import com.mysql.cj.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CSVgeneric {
    private String filepath;
    protected CSVgeneric(String fp) {
        filepath = fp;
    }

    protected ArrayList<String> readAll() {
        ArrayList<String> objArray = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(filepath));
            scanner.useDelimiter(",|\\n");

            while (scanner.hasNext()) {
                objArray.add(scanner.next());
            }
        }
        catch (Exception e) {

        }

        return objArray;
    }

    // this gets all properties of an object
    // and adds them to the CSV file
    protected void addObject(Object obj) throws IOException, IllegalAccessException {
        FileWriter fw = new FileWriter(filepath,true);

        String newObject = "";

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            newObject += field.get(obj) + ",";
        }

        newObject = newObject.substring(0, newObject.length() - 1);
        newObject += "\n";

        fw.write(newObject);

        fw.close();
    }
}
