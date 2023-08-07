package kr.smhrd.model;
import java.util.List;

import lombok.Data;

@Data
public class Trip {
    private int trip_id;
    private String trip_name;
    private String start_date;
    private String end_date;
    private List<Itinerary> itineraries;

    // Add getters and setters for the fields
    // ...
}
