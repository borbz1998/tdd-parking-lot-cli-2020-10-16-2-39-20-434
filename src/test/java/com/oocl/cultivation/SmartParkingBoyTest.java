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
    private Car car;

    private ParkingBoyList parkingBoyList = new ParkingBoyList();

    private ParkingLotManager parkingLotManager;
    List<ParkingLot> parkingLotListByManager = new ArrayList<>();
    private ParkingLotList parkingLotList;

    private SmartParkingBoy smartParkingBoy;

    private String currentCarLocation = "";


    @BeforeEach
    void setUp() {
        car = new Car();

        parkingLotList = new ParkingLotList();

        parkingLotListByManager.add(new ParkingLot(10));
        parkingLotListByManager.add(new ParkingLot(5));

        parkingBoyList = new ParkingBoyList(
                smartParkingBoy,
                new SmartParkingBoy(new ParkingLot(parkingLotListByManager)),
                new SuperSmartParkingBoy(new ParkingLot(parkingLotListByManager)));

        smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotListByManager));

        parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotListByManager));

        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager, parkingLotList);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given

        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        ParkingTicket parkingTicket = smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchCar = smartParkingBoy.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car secondCar = new Car();
        ParkingTicket parkingTicket1 = smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket parkingTicket2 = smartParkingBoy.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchFirstCar = smartParkingBoy.fetch(parkingTicket1, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        Car fetchSecondCar = smartParkingBoy.fetch(parkingTicket2, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_given_wrong_ticket() {
        //given

        //when
        smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        assertThrows(WrongTicketException.class, () -> smartParkingBoy.fetch(wrongTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given

        //when
        smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket noTicket = null;

        //then
        assertThrows(NoTicketException.class, () -> smartParkingBoy.fetch(noTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Please provide your parking ticket!");
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingTicket parkingTicket = smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchCarFirstTime = smartParkingBoy.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> smartParkingBoy.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(new ParkingLot(1));
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotLists));
        ParkingBoyList parkingBoyList = new ParkingBoyList(smartParkingBoy);
        Car secondCar = new Car();
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotLists, parkingLotList);

        //when
        smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> smartParkingBoy.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Not Enough Position.");
    }

    @Test
    public void should_return_car_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot_with_same_available_space() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(5));
        parkingLotMapLists.add(new ParkingLot(5));

        Car secondCar = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotMapLists));
        ParkingBoyList parkingBoyList = new ParkingBoyList(smartParkingBoy);

        //when
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotMapLists, parkingLotList);
        // Park at Parking Lot 1
        smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        // Park at Parking Lot 2
        ParkingTicket parkingTicket1 = smartParkingBoy.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        currentCarLocation = smartParkingBoy.getCurrentLocation(parkingLotMapLists, parkingTicket1);

        //then
        assertNotNull(parkingTicket1);
        assertSame(secondCar, smartParkingBoy.fetch(parkingTicket1, parkingBoyList.getParkingBoyList().get(0), parkingLotList));

        assertEquals("ParkingLot Number: 2", currentCarLocation);
    }

    @Test
    public void should_return_parking_lot_where_car_is_park_when_smart_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(1));
        parkingLotMapLists.add(new ParkingLot(2));

        Car secondCar = new Car();
        Car thirdCar = new Car();

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot(parkingLotMapLists));
        ParkingBoyList parkingBoyList = new ParkingBoyList(smartParkingBoy);

        //when
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotMapLists, parkingLotList);
        // Park at Parking Lot 2
        smartParkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        // Park at Parking Lot 1
        ParkingTicket parkingTicket2 = smartParkingBoy.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        // Park at Parking Lot 2
        smartParkingBoy.park(thirdCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        currentCarLocation = smartParkingBoy.getCurrentLocation(parkingLotMapLists, parkingTicket2);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, smartParkingBoy.fetch(parkingTicket2, parkingBoyList.getParkingBoyList().get(0), parkingLotList));
        assertEquals("ParkingLot Number: 1", currentCarLocation);
    }
}