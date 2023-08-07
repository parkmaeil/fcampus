package kr.smhrd.javase;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kr.smhrd.model.*;

public class TripCreator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 여행 정보를 사용자로부터 입력 받습니다.
        System.out.println("여행 ID를 입력하세요: ");
        int tripId = scanner.nextInt();
        scanner.nextLine(); // 개행 문자를 소모합니다.

        System.out.println("여행 이름을 입력하세요: ");
        String tripName = scanner.nextLine();

        System.out.println("시작 날짜를 입력하세요 (YYYY-MM-DD 형식): ");
        String startDate = scanner.nextLine();

        System.out.println("종료 날짜를 입력하세요 (YYYY-MM-DD 형식): ");
        String endDate = scanner.nextLine();

        // 일정 정보를 저장할 리스트를 생성합니다.
        List<Itinerary> itineraries = new ArrayList<>();

        // 일정 정보를 사용자로부터 입력 받습니다.
        while (true) {
            System.out.println("일정 유형을 입력하세요 (travel/ lodging): ");
            String type = scanner.nextLine();

            if (!type.equals("travel") && !type.equals("lodging")) {
                System.out.println("유효하지 않은 일정 유형입니다. 'travel' 또는 'lodging'을 입력하세요.");
                continue;
            }

            System.out.println("출발지를 입력하세요: ");
            String departure = scanner.nextLine();

            System.out.println("도착지를 입력하세요: ");
            String destination = scanner.nextLine();

            System.out.println("출발 시간을 입력하세요 (YYYY-MM-DD HH:mm 형식): ");
            String departureTime = scanner.nextLine();

            System.out.println("도착 시간을 입력하세요 (YYYY-MM-DD HH:mm 형식): ");
            String arrivalTime = scanner.nextLine();

            // 숙박 일정인 경우 추가 정보를 입력 받습니다.
            String accommodation = "";
            String checkIn = "";
            String checkOut = "";

            if (type.equals("lodging")) {
                System.out.println("숙박 장소를 입력하세요: ");
                accommodation = scanner.nextLine();

                System.out.println("체크인 시간을 입력하세요 (YYYY-MM-DD HH:mm 형식): ");
                checkIn = scanner.nextLine();

                System.out.println("체크아웃 시간을 입력하세요 (YYYY-MM-DD HH:mm 형식): ");
                checkOut = scanner.nextLine();
            }

            // 일정 객체를 생성합니다.
            Itinerary itinerary = new Itinerary();
            itinerary.setItinerary_id(itineraries.size() + 1);
            itinerary.setType(type);
            itinerary.setDeparture(departure);
            itinerary.setDestination(destination);
            itinerary.setDeparture_time(departureTime);
            itinerary.setArrival_time(arrivalTime);
            itinerary.setAccommodation(accommodation);
            itinerary.setCheck_in(checkIn);
            itinerary.setCheck_out(checkOut);

            // 일정 리스트에 추가합니다.
            itineraries.add(itinerary);

            // 더 많은 일정을 추가할지 여부를 사용자에게 묻습니다.
            System.out.println("다른 일정을 추가하시겠습니까? (yes/no): ");
            String addAnother = scanner.nextLine();
            if (!addAnother.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // 여행 정보와 일정 정보를 담는 Trip 객체를 생성합니다.
        Trip trip = new Trip();
        trip.setTrip_id(tripId);
        trip.setTrip_name(tripName);
        trip.setStart_date(startDate);
        trip.setEnd_date(endDate);
        trip.setItineraries(itineraries);

        // Trip 객체를 JSON 형식으로 변환합니다.
        Gson gson = new Gson();
        String jsonData = gson.toJson(List.of(trip));

        // JSON 데이터를 trips.json 파일로 저장합니다.
        try (FileWriter writer = new FileWriter("trips.json")) {
            writer.write(jsonData);
            System.out.println("데이터가 성공적으로 trips.json 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 스캐너를 닫습니다.
        scanner.close();
    }
}