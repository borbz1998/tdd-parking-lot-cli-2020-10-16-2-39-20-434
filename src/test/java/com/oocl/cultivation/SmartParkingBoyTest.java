package com.oocl.cultivation;

import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.Exception.NoTicketException;
import com.oocl.cultivation.Exception.WrongTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {
    private List<ParkingLot> parkingLotMapList;
    private Car car;

    @BeforeEach
    void setUp() {
        parkingLotMapList = new ArrayList<>();
        parkingLotMapList.add(new ParkingLot(10));
        car = new Car();
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotMapList));

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotMapList));
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car fetchCar = smartParkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car secondCar = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotMapList));
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(secondCar);

        //when
        Car fetchFirstCar = smartParkingBoy.fetch(parkingTicket1);
        Car fetchSecondCar = smartParkingBoy.fetch(parkingTicket2);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_given_wrong_ticket() {
        //given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotMapList));
        smartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //when


        //then
        assertThrows(WrongTicketException.class, () -> smartParkingBoy.fetch(wrongTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotMapList));
        smartParkingBoy.park(car);
        ParkingTicket noTicket = null;

        //when

        //then
        assertThrows(NoTicketException.class, () -> smartParkingBoy.fetch(noTicket), "Please provide your parking ticket!");
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(parkingLotMapList);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car fetchCarFirstTime = smartParkingBoy.fetch(parkingTicket);
//        parkingLot.removeCarFromParkingLot(parkingTicket, parkingLotMapList);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> smartParkingBoy.fetch(parkingTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotLists));
        Car secondCar = new Car();

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> smartParkingBoy.park(secondCar), "Not Enough Position.");
    }

    @Test
    public void should_return_car_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(1));
        parkingLotMapLists.add(new ParkingLot(1));

        Car secondCar = new Car();
        Car thirdCar = new Car();


        ParkingLot parkingLot = new ParkingLot(parkingLotMapLists);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);

        //when
        smartParkingBoy.park(car);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(secondCar);
//        ParkingTicket parkingTicket3 = smartParkingBoy.park(thirdCar);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, smartParkingBoy.fetch(parkingTicket2));
    }

    @Test
    public void should_return_parking_lot_where_car_is_park_when__smart_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(1));
        parkingLotMapLists.add(new ParkingLot(5));

        Car secondCar = new Car();
        Car thirdCar = new Car();


        ParkingLot parkingLot = new ParkingLot(parkingLotMapLists);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);

        //when
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(secondCar);
        ParkingTicket parkingTicket3 = smartParkingBoy.park(thirdCar);
        String currentLocation = smartParkingBoy.getCurrentLocation(parkingLotMapLists, parkingTicket2);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, smartParkingBoy.fetch(parkingTicket2));
        assertEquals("ParkingLot Number: 2", currentLocation);
    }
}