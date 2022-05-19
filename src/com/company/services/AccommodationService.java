package com.company.services;

import Objects.Accommodation;
import Objects.Reminder;
import com.company.JDBCgeneric;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccommodationService extends JDBCgeneric {
    final private String tableName = "accomodations";

    public void addAccommodation(Accommodation accommodation) {
        String addQuery = "insert into " + tableName + "(name, location, price) values('" +
                accommodation.getName() + "', '" + accommodation.getLocation() + "', " + accommodation.getPrice() + ")";

        executeSQLupdate(addQuery);
    }

    public ArrayList<Accommodation> getAccommodations() {
        String selectQuery = "select * from " + tableName;

        ArrayList<Accommodation> accommodations = new ArrayList<Accommodation>();
        ResultSet results = executeSQLquery(selectQuery);

        try {
            while (results.next()) {
                Accommodation currentAccommodation = new Accommodation();
                currentAccommodation.setId(results.getInt("Id"));
                currentAccommodation.setName(results.getString("name"));
                currentAccommodation.setLocation(results.getString("location"));
                currentAccommodation.setPrice(results.getInt("price"));

                accommodations.add(currentAccommodation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accommodations;
    }

    public void removeAccommodation(int idx) {
        String deleteQuery = "delete from " + tableName + " where Id=" + idx;
        executeSQLupdate(deleteQuery);
    }
}
