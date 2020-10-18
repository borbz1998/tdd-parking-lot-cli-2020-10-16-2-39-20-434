package com.oocl.cultivation;

import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.Exception.NoTicketException;
import com.oocl.cultivation.Exception.WrongTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    private Car car;

    private ParkingBoyList parkingBoyList = new ParkingBoyList();

    private ParkingLotManager parkingLotManager;
    List<ParkingLot> parkingLotListByManager = new ArrayList<>();
    private ParkingLotList parkingLotList;

    private ParkingBoy parkingBoy;

    @BeforeEach
    void setup() {
        car = new Car();

        parkingLotList = new ParkingLotList();

        parkingLotListByManager.add(new ParkingLot(10));
        parkingLotListByManager.add(new ParkingLot(5));

        parkingBoyList = new ParkingBoyList(
                parkingBoy,
                new SmartParkingBoy(new ParkingLot(parkingLotListByManager)),
                new SuperSmartParkingBoy(new ParkingLot(parkingLotListByManager)));

        parkingBoy = new ParkingBoy(new ParkingLot(parkingLotListByManager));

        parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotListByManager));

        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager, parkingLotList);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        ParkingTicket parkingTicket = parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car secondCar = new Car();
        ParkingTicket parkingTicket1 = parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchFirstCar = parkingBoy.fetch(parkingTicket1, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        Car fetchSecondCar = parkingBoy.fetch(parkingTicket2, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_given_wrong_ticket() {
        //given

        //when
        parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(wrongTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given

        //when
        parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket noTicket = null;

        //then
        assertThrows(NoTicketException.class, () -> parkingBoy.fetch(noTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Please provide your parking ticket!");
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingTicket parkingTicket = parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchCarFirstTime = parkingBoy.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot(parkingLotLists));
        Car secondCar = new Car();

        //when
        parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> parkingBoy.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Not Enough Position.");
    }

    @Test
    public void should_return_car_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(2));
        parkingLotMapLists.add(new ParkingLot(1));

        Car secondCar = new Car();

        ParkingLot parkingLot = new ParkingLot(parkingLotMapLists);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        parkingBoy.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        String currentCarLocation = parkingLotManager.getCurrentLocation(parkingLotMapLists, parkingTicket2);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, parkingBoy.fetch(parkingTicket2, parkingBoyList.getParkingBoyList().get(0), parkingLotList));

        assertEquals("ParkingLot Number: 1", currentCarLocation);
    }
}
