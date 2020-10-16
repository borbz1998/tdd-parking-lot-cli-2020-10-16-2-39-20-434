package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    private List<ParkingLot> listOfMap;
    private Car car;

    @BeforeEach
    public void setup() {
        listOfMap = new ArrayList<>();
        listOfMap.add(new ParkingLot(10));
        car = new Car();
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(listOfMap));

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(listOfMap));
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car secondCar = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(listOfMap));
        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar);

        //when
        Car fetchFirstCar = parkingBoy.fetch(parkingTicket1);
        Car fetchSecondCar = parkingBoy.fetch(parkingTicket2);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_no_car_when_fetch_given_wrong_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(listOfMap));
        parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //when


        //then
        assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(wrongTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_no_car_when_fetch_given_no_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(listOfMap));
        parkingBoy.park(car);
        ParkingTicket noTicket = null;

        //when

        //then
        assertThrows(NoTicketException.class, () -> parkingBoy.fetch(noTicket), "Please provide your parking ticket!");
    }

    @Test
    public void should_return_no_car_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(listOfMap);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCarFirstTime = parkingBoy.fetch(parkingTicket);
        parkingLot.removeCarFromParkingLot(parkingTicket, listOfMap);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(parkingTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_no_ticket_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(parkingLotLists));
        Car secondCar = new Car();

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> parkingBoy.park(secondCar), "Not Enough Position.");
    }
    
    @Test
    public void should_return_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> listOfMaps = new ArrayList<>();
        listOfMaps.add(new ParkingLot(1));
        listOfMaps.add(new ParkingLot(1));

        Car secondCar = new Car();
        Car thirdCar = new Car();


        ParkingLot parkingLot = new ParkingLot(listOfMaps);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, parkingBoy.fetch(parkingTicket2));
    }

//    @Test
//    public void should_return_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
//        //given
//        List<ParkingLot> listOfMaps = new ArrayList<>();
//        listOfMaps.add(new ParkingLot(3));
//        listOfMaps.add(new ParkingLot(2));
//        Car firstCar = new Car();
//        Car firstCar2 = new Car();
//        Car firstCar3 = new Car();
//        Car firstCar4 = new Car();
//        Car firstCar5 = new Car();
//        ParkingLot parkingLot = new ParkingLot(listOfMaps);
//
//        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
//
//        //when
//        ParkingTicket parkingTicket = parkingBoy.park(firstCar);
//        ParkingTicket parkingTicketA = parkingBoy.park(firstCar2);
//        ParkingTicket parkingTicketB = parkingBoy.park(firstCar3);
//        ParkingTicket parkingTicketC = parkingBoy.park(firstCar4);
//        ParkingTicket parkingTicket2 = parkingBoy.park(firstCar5);
//
//        //then
//        assertNotNull(parkingTicket2);
//        assertSame(firstCar5, parkingBoy.fetch(parkingTicket2));
//    }

}
