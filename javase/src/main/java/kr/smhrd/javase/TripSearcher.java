package kr.smhrd.javase;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import kr.smhrd.model.*;

public class TripSearcher {
    public static void main(String[] args) {
        int targetTripId = 1; // 원하는 여행의 trip_id로 변경하세요.

        // JSON 파일로부터 데이터를 로드
        String jsonData = loadJsonFromFile("trips.json");
        
        // JSON 데이터를 Trip 객체들의 리스트로 파싱
        List<Trip> trips = parseJsonToTrips(jsonData);

        // trip_id로 원하는 여행을 검색
        Trip targetTrip = findTripById(trips, targetTripId);

        // 찾은 여행의 일정을 출력
        if (targetTrip != null) {
            System.out.println("여행 ID: " + targetTrip.getTrip_id());
            System.out.println("여행 이름: " + targetTrip.getTrip_name());
            System.out.println("시작 날짜: " + targetTrip.getStart_date());
            System.out.println("종료 날짜: " + targetTrip.getEnd_date());

            List<Itinerary> itineraries = targetTrip.getItineraries();
            System.out.println("여정일정:");
            System.out.println("---------------------------------------");
            for (Itinerary itinerary : itineraries) {
                System.out.println("유형: " + itinerary.getType());
                System.out.println("출발지: " + itinerary.getDeparture());
                System.out.println("도착지: " + itinerary.getDestination());
                System.out.println("출발 시간: " + itinerary.getDeparture_time());
                System.out.println("도착 시간: " + itinerary.getArrival_time());

                // 숙박 일정인지 확인하고 추가적인 숙박 정보를 출력합니다.
                if ("lodging".equals(itinerary.getType())) {
                    System.out.println("숙박 장소: " + itinerary.getAccommodation());
                    System.out.println("체크인: " + itinerary.getCheck_in());
                    System.out.println("체크아웃: " + itinerary.getCheck_out());
                }
                System.out.println("---------------------------------------");
            }
        } else {
            System.out.println("ID가 " + targetTripId + "인 여행을 찾을 수 없습니다.");
        }
    }

    private static String loadJsonFromFile(String filePath) {
        StringBuilder jsonData = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonData.toString();
    }

    private static List<Trip> parseJsonToTrips(String jsonData) {
        Gson gson = new Gson();
        JsonElement jsonElement = JsonParser.parseString(jsonData);

        if (jsonElement.isJsonObject()) {
            // If the JSON contains a single object, create a list with a single trip
            Trip trip = gson.fromJson(jsonElement, Trip.class);
            List<Trip> singleTripList = new ArrayList<>();
            singleTripList.add(trip);
            return singleTripList;
        } else if (jsonElement.isJsonArray()) {
            // If the JSON contains an array, parse it as a list of trips
            return gson.fromJson(jsonElement, new TypeToken<List<Trip>>() {}.getType());
        } else {
            // Handle other JSON formats or error cases
            return Collections.emptyList();
        }
    }

    private static Trip findTripById(List<Trip> trips, int targetTripId) {
        for (Trip trip : trips) {
            if (trip.getTrip_id() == targetTripId) {
                return trip;
            }
        }
        return null;
    }
}
