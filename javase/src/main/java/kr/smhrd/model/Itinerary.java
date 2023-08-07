package kr.smhrd.model;

import lombok.Data;

@Data
public class Itinerary {
	 private int itinerary_id;
	    private String type;
	    private String departure;
	    private String destination;
	    private String departure_time;
	    private String arrival_time;
	    private String accommodation; // For lodging itinerary
	    private String check_in; // For lodging itinerary
	    private String check_out; // For lodging itinerary

    // Add getters and setters for the fields
    // ...
}
