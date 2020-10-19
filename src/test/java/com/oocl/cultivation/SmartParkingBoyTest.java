package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoParkingLotSpaceException;
import com.oocl.cultivation.exception.NoTicketException;
import com.oocl.cultivation.exception.WrongTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    private Car car;
    private List<ParkingLot> parkingLotList;
    private StringBuffer currentCarLocation;

    @BeforeEach
    void setUp() {
        car = new Car();
        currentCarLocation = new StringBuffer();
        ParkingLot newParkingLot = new ParkingLot(5);
        ParkingLot newParkingLot2 = new ParkingLot(5);
        parkingLotList = Arrays.asList(newParkingLot, newParkingLot2);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car fetchCar = smartParkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        Car secondCar = new Car();
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
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        smartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> smartParkingBoy.fetch(wrongTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);


        //when
        smartParkingBoy.park(car);
        ParkingTicket noTicket = null;

        //then
        NoTicketException noTicketException = assertThrows(NoTicketException.class, () -> smartParkingBoy.fetch(noTicket));
        assertEquals("Please provide your parking ticket!", noTicketException.getMessage());
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car fetchCarFirstTime = smartParkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCarFirstTime);
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> smartParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotLists);
        Car secondCar = new Car();

        //when
        smartParkingBoy.park(car);

        //then
        NoParkingLotSpaceException noParkingLotSpaceException = assertThrows(NoParkingLotSpaceException.class, () -> smartParkingBoy.park(secondCar));
        assertEquals("Not Enough Position.", noParkingLotSpaceException.getMessage());
    }

    @Test
    public void should_return_car_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot_with_same_available_space() {
        //given
        Car secondCar = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);

        //when
        // Park at Parking Lot 1
        smartParkingBoy.park(car);
        // Park at Parking Lot 2
        ParkingTicket parkingTicket1 = smartParkingBoy.park(secondCar);
        currentCarLocation.append(smartParkingBoy.getCurrentLocation(parkingLotList, parkingTicket1));

        //then
        assertNotNull(parkingTicket1);
        assertSame(secondCar, smartParkingBoy.fetch(parkingTicket1));

        assertEquals("ParkingLot Number: 2", currentCarLocation.toString());
    }

    @Test
    public void should_return_parking_lot_where_car_is_park_when_smart_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(1));
        parkingLotList.add(new ParkingLot(2));

        Car secondCar = new Car();
        Car thirdCar = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        ParkingBoyList parkingBoyList = new ParkingBoyList(smartParkingBoy);

        //when
        // Park at Parking Lot 2
        smartParkingBoy.park(car);
        // Park at Parking Lot 1
        ParkingTicket parkingTicket2 = smartParkingBoy.park(secondCar);
        currentCarLocation.append(smartParkingBoy.getCurrentLocation(parkingLotList, parkingTicket2));

        //then
        assertNotNull(parkingTicket2);
        assertEquals("ParkingLot Number: 1", currentCarLocation.toString());
    }
}