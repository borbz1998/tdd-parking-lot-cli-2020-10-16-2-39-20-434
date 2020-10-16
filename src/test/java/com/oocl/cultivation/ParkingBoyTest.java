package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(10));

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);

    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given    
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(10));
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(10));
        ParkingTicket parkingTicket1 = parkingBoy.park(firstCar);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar);

        //when
        Car fetchFirstCar = parkingBoy.fetch(parkingTicket1);
        Car fetchSecondCar = parkingBoy.fetch(parkingTicket2);

        //then
        assertSame(firstCar, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_no_car_when_fetch_given_wrong_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(10));
        parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //when

        //then
        assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(wrongTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_no_car_when_fetch_given_no_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(10));
        parkingBoy.park(car);
        ParkingTicket noTicket = null;

        //when

        //then
        assertThrows(NoTicketException.class, () -> parkingBoy.fetch(noTicket), "Not Enough Position.");
    }

    @Test
    public void should_return_no_car_when_fetch_a_car_given_used_ticket() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCarFirstTime = parkingBoy.fetch(parkingTicket);
        parkingLot.removeCarFromParkingLot(parkingTicket);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(parkingTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_no_ticket_when_park_a_car_given_parking_lot_is_full() {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(firstCar);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> parkingBoy.park(secondCar), "Please provide your parking ticket!");
    }
    
    @Test
    public void should_return_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<Map> listOfMap = new ArrayList<>();
        listOfMap.add(new HashMap(1));
        listOfMap.add(new HashMap(1));
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingLot parkingLot = new ParkingLot(listOfMap);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        
        //when
        ParkingTicket parkingTicket = parkingBoy.park(firstCar);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, parkingBoy.fetch(parkingTicket2));
    }

}
