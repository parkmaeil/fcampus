package kr.smhrd.javase;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kr.smhrd.model.*;

public class TripPartSearcher {
    // ... (이전에 정의된 메서드들)
	// 여행 일정만 검색하는 메서드
    private static List<Itinerary> findTravelItineraries(List<Itinerary> itineraries) {
        List<Itinerary> travelItineraries = new ArrayList<>();
        for (Itinerary itinerary : itineraries) {
            if ("travel".equals(itinerary.getType())) {
                travelItineraries.add(itinerary);
            }
        }
        return travelItineraries;
    }

    // 숙박 일정만 검색하는 메서드
    private static List<Itinerary> findLodgingItineraries(List<Itinerary> itineraries) {
        List<Itinerary> lodgingItineraries = new ArrayList<>();
        for (Itinerary itinerary : itineraries) {
            if ("lodging".equals(itinerary.getType())) {
                lodgingItineraries.add(itinerary);
            }
        }
        return lodgingItineraries;
    }
    
 // JSON 데이터를 파일에서 로드하는 메서드
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

    // JSON 데이터를 Trip 객체들의 리스트로 파싱하는 메서드
    private static List<Trip> parseJsonToTrips(String jsonData) {
        Gson gson = new Gson();
        return gson.fromJson(jsonData, new TypeToken<List<Trip>>() {}.getType());
    }

    // trip_id로 여행을 검색하는 메서드
    private static Trip findTripById(List<Trip> trips, int targetTripId) {
        for (Trip trip : trips) {
            if (trip.getTrip_id() == targetTripId) {
                return trip;
            }
        }
        return null;
    }
    
    public static void main(String[] args) {
        // JSON 데이터를 파일에서 로드합니다.
        String jsonData = loadJsonFromFile("trips.json");

        // JSON 데이터를 Trip 객체들의 리스트로 파싱합니다.
        List<Trip> trips = parseJsonToTrips(jsonData);

        // 사용자로부터 찾고자 하는 여행의 trip_id를 입력받습니다.
        Scanner scanner = new Scanner(System.in);
        System.out.println("검색할 여행의 ID를 입력하세요: ");
        int targetTripId = scanner.nextInt();

        // 사용자가 입력한 trip_id로 여행을 검색합니다.
        Trip targetTrip = findTripById(trips, targetTripId);

        // 찾은 여행의 정보를 출력합니다.
        if (targetTrip != null) {
            System.out.println("여행 ID: " + targetTrip.getTrip_id());
            System.out.println("여행 이름: " + targetTrip.getTrip_name());
            System.out.println("시작 날짜: " + targetTrip.getStart_date());
            System.out.println("종료 날짜: " + targetTrip.getEnd_date());

            List<Itinerary> itineraries = targetTrip.getItineraries();
            System.out.println("일정:");

            // 사용자로부터 검색할 일정 유형(여행 또는 숙박)을 입력받습니다.
            System.out.println("검색할 일정 유형을 입력하세요 (travel/lodging): ");
            String targetType = scanner.next().toLowerCase();

            if ("travel".equals(targetType)) {
                // 여행 일정을 검색하고 출력합니다.
                List<Itinerary> travelItineraries = findTravelItineraries(itineraries);
                System.out.println("여행 일정:");
                for (Itinerary travel : travelItineraries) {
                    System.out.println("여행 ID: " + travel.getItinerary_id());
                    System.out.println("출발지: " + travel.getDeparture());
                    System.out.println("도착지: " + travel.getDestination());
                    System.out.println("출발 시간: " + travel.getDeparture_time());
                    System.out.println("도착 시간: " + travel.getArrival_time());
                    System.out.println();
                }
            } else if ("lodging".equals(targetType)) {
                // 숙박 일정을 검색하고 출력합니다.
                List<Itinerary> lodgingItineraries = findLodgingItineraries(itineraries);
                System.out.println("숙박 일정:");
                for (Itinerary lodging : lodgingItineraries) {
                    System.out.println("숙박 ID: " + lodging.getItinerary_id());
                    System.out.println("도착지: " + lodging.getDestination());
                    System.out.println("체크인: " + lodging.getCheck_in());
                    System.out.println("체크아웃: " + lodging.getCheck_out());
                    System.out.println();
                }
            } else {
                System.out.println("잘못된 일정 유형입니다. 'travel' 또는 'lodging' 중 하나를 입력하세요.");
            }
        } else {
            System.out.println("ID가 " + targetTripId + "인 여행을 찾을 수 없습니다.");
        }

        // 스캐너를 닫습니다.
        scanner.close();
    }
}
