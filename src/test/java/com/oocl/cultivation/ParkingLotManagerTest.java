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

class ParkingLotManagerTest {

    private Car car;
    private List<ParkingLot> parkingLotList;

    @BeforeEach
    void setUp() {

        car = new Car();

        ParkingLot newParkingLot = new ParkingLot(5);
        ParkingLot newParkingLot2 = new ParkingLot(5);
        parkingLotList = Arrays.asList(newParkingLot, newParkingLot2);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotList);

        //when
        ParkingTicket parkingTicket = parkingLotManager.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotList);
        ParkingTicket parkingTicket = parkingLotManager.park(car);

        //when
        Car fetchCar = parkingLotManager.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotList);
        Car secondCar = new Car();
        ParkingTicket parkingTicket1 = parkingLotManager.park(car);
        ParkingTicket parkingTicket2 = parkingLotManager.park(secondCar);

        //when
        Car fetchFirstCar = parkingLotManager.fetch(parkingTicket1);
        Car fetchSecondCar = parkingLotManager.fetch(parkingTicket2);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_given_wrong_ticket() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotList);

        //when
        parkingLotManager.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> parkingLotManager.fetch(wrongTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotList);


        //when
        parkingLotManager.park(car);
        ParkingTicket noTicket = null;

        //then
        NoTicketException noTicketException = assertThrows(NoTicketException.class, () -> parkingLotManager.fetch(noTicket));
        assertEquals("Please provide your parking ticket!", noTicketException.getMessage());
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotList);
        ParkingTicket parkingTicket = parkingLotManager.park(car);

        //when
        Car fetchCarFirstTime = parkingLotManager.fetch(parkingTicket);

        //then
        assertSame(car, fetchCarFirstTime);
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> parkingLotManager.fetch(parkingTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotLists);
        Car secondCar = new Car();

        //when
        parkingLotManager.park(car);

        //then
        NoParkingLotSpaceException noParkingLotSpaceException = assertThrows(NoParkingLotSpaceException.class, () -> parkingLotManager.park(secondCar));
        assertEquals("Not Enough Position.", noParkingLotSpaceException.getMessage());
    }


    @Test
    public void should_return_car_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(new ParkingLot(2));
        parkingLotLists.add(new ParkingLot(1));

        Car secondCar = new Car();

        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotLists);
        ParkingBoyList parkingBoyList = new ParkingBoyList(parkingLotManager);

        //when
        parkingLotManager.park(car);
        ParkingTicket parkingTicket2 = parkingLotManager.park(secondCar);
        StringBuffer currentCarLocation = new StringBuffer();
        currentCarLocation.append(parkingLotManager.getCurrentLocation(parkingLotLists, parkingTicket2));

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, parkingLotManager.fetch(parkingTicket2));

        assertEquals("ParkingLot Number: 1", currentCarLocation.toString());
    }

    @Test
    public void should_return_parking_boy_list_when_parking_lot_manager_add_new_parking_boy_given_there_is_parking_boy_list() {
        //given
        List<ParkingLot> parkingLotMapList = new ArrayList<>();
        parkingLotMapList.add(new ParkingLot(2));

        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLotMapList);
        ParkingBoy newParkingBoy = new ParkingBoy(parkingLotMapList);
        ParkingBoyList parkingBoyList = new ParkingBoyList(
                new ParkingBoy(parkingLotMapList),
                new SmartParkingBoy(parkingLotMapList),
                new SuperSmartParkingBoy(parkingLotMapList));

        //when
        parkingLotManager.addNewParkingBoy(parkingBoyList, newParkingBoy);

        //then
        assertEquals(4, parkingBoyList.getParkingBoyList().size());
    }
}