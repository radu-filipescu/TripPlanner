package com.company.CSVservices;

import Objects.Accommodation;
import com.company.CSVgeneric;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccommodationServiceCSV extends CSVgeneric {
    public AccommodationServiceCSV(String filepath) throws FileNotFoundException {
        super(filepath);
    }

    // for auto-increment, to replicate SQL behaviour
    private static int lastId = 0;

    public void addAccommodation(Accommodation accommodation) {
        // auto-increment Id
        ++lastId;
        accommodation.setId(lastId);

        try {
            addObject(accommodation);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Accommodation> getAccommodations() {
        ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
        ArrayList<String> raw = readAll();

        for(int i = 4; i < raw.size(); i = i + 4) {
            Accommodation accommodation = new Accommodation();

            accommodation.setId(Integer.parseInt(raw.get(i)));
            accommodation.setName(raw.get(i + 1));
            accommodation.setLocation(raw.get(i + 2));
            accommodation.setPrice(Integer.parseInt(raw.get(i + 3)));

            accommodations.add(accommodation);
        }

        return accommodations;
    }
}

