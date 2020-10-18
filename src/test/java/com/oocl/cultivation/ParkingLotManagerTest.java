package com.oocl.cultivation;

import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.Exception.NoTicketException;
import com.oocl.cultivation.Exception.NotYourParkingLotException;
import com.oocl.cultivation.Exception.WrongTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagerTest {
    private List<ParkingLot> parkingLotMapList;
    private Car car;
    private ParkingBoyList parkingBoyList = new ParkingBoyList();

    private ParkingLotManager parkingLotManager;
    private List<ParkingLot> parkingLotListByManager = new ArrayList<>();
    private ParkingLotList parkingLotList;

    @BeforeEach
    void setUp() {
        car = new Car();

        parkingLotList = new ParkingLotList();

        parkingBoyList = new ParkingBoyList(
                parkingLotManager,
                new ParkingBoy(new ParkingLot(parkingLotListByManager)),
                new SmartParkingBoy(new ParkingLot(parkingLotListByManager)),
                new SuperSmartParkingBoy(new ParkingLot(parkingLotListByManager)));

        parkingLotListByManager.add(new ParkingLot(10));
        parkingLotListByManager.add(new ParkingLot(5));

        parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotListByManager));

        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager, parkingLotList);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given

        //when
        ParkingTicket parkingTicket = parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given

        ParkingTicket parkingTicket = parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchCar = parkingLotManager.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car secondCar = new Car();
        ParkingTicket parkingTicket1 = parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket parkingTicket2 = parkingLotManager.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchFirstCar = parkingLotManager.fetch(parkingTicket1, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        Car fetchSecondCar = parkingLotManager.fetch(parkingTicket2, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_given_wrong_ticket() {
        //given
        Car car = new Car();
        parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket wrongTicket = new ParkingTicket();

        //when

        //then
        assertThrows(WrongTicketException.class, () -> parkingLotManager.fetch(wrongTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given

        //when
        parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket noTicket = null;

        //then
        assertThrows(NoTicketException.class, () -> parkingLotManager.fetch(noTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Please provide your parking ticket!");
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingTicket parkingTicket = parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //when
        Car fetchCarFirstTime = parkingLotManager.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> parkingLotManager.fetch(parkingTicket, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotLists));
        ParkingBoyList parkingBoyList = new ParkingBoyList(parkingLotManager);
        Car secondCar = new Car();
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotLists, parkingLotList);

        //when
        parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> parkingLotManager.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList), "Not Enough Position.");
    }

    @Test
    public void should_return_car_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(1));
        parkingLotMapLists.add(new ParkingLot(1));

        Car secondCar = new Car();

        //when
        parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(0), parkingLotList);
        ParkingTicket parkingTicket2 = parkingLotManager.park(secondCar, parkingBoyList.getParkingBoyList().get(0), parkingLotList);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, parkingLotManager.fetch(parkingTicket2, parkingBoyList.getParkingBoyList().get(0), parkingLotList));
    }

    @Test
    public void should_return_parking_boy_list_when_parking_lot_manager_add_new_parking_boy_given_there_is_parking_boy_list() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
        ParkingBoy newParkingBoy = new ParkingBoy(new ParkingLot(parkingLotMapList));
        ParkingBoyList parkingBoyList = new ParkingBoyList(
                new ParkingBoy(new ParkingLot(parkingLotMapList)),
                new SmartParkingBoy(new ParkingLot(parkingLotMapList)),
                new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList)));

        //when
        parkingLotManager.addNewParkingBoy(parkingBoyList, newParkingBoy);

        //then
        assertEquals(4, parkingBoyList.getParkingBoyList().size());
    }

    @Test
    public void should_return_parking_boy_with_assigned_parking_lot_when_parking_lot_manager_assigned_parking_lot_to_parking_boy_given_there_is_parking_boy_list_and_parking_lot_maps() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotListByManager));

        //when
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager, parkingLotList);

        //then
        // Check the size of the map
        assertEquals(1, parkingLotManager.getParkingBoyWithParkingLotMap(parkingLotList));
        // Check if Map is empty -- expected not null
        assertNotNull(parkingLotList.getParkingBoyParkingLotMap());
        // Check if the Map in the ParkingLotList with Key (First Parking Boy in the list) has value
        assertNotNull(parkingLotList.getParkingBoyParkingLotMap().containsKey(parkingBoyList.getParkingBoyList().get(0)));
    }

    @Test
    public void should_return_NotYourParkingLotException_when_parking_lot_manager_assigned_parking_boy_to_park_a_car_given_it_is_not_his_parking_lot() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotListByManager));

        //when
        // Assigned ParkingLotManager to the first parking lot in the list
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager, parkingLotList);

        //then
        // Check the size of the map (ParkingLotManager with assigned ParkingLotList)
        assertEquals(1, parkingLotManager.getParkingBoyWithParkingLotMap(parkingLotList));
        // Check if Map is empty -- expected not null
        assertNotNull(parkingLotManager.getParkingBoyParkingLotMap());
        // Check if the Map in the ParkingLotList with Key (First Parking Boy in the list) has value
        assertNotNull(parkingLotList.getParkingBoyParkingLotMap().containsKey(parkingBoyList.getParkingBoyList().get(0)));

        // Expected to throw exception
        assertThrows(NotYourParkingLotException.class, () -> parkingLotManager.park(car, parkingBoyList.getParkingBoyList().get(3), parkingLotList), "Sorry Not Your Parking Lot!");
    }

//    @Test
//    public void should_return_parking_boy_parks_a_car_when_parking_lot_manager_ask_parking_boy_to_park_a_car_given_it_is_his_parking_lot() {
//        //given
//        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
//        List<ParkingLot> parkingLotListByManager = new ArrayList<>();
//
//        parkingLotListByManager.add(new ParkingLot(10));
//        parkingLotListByManager.add(new ParkingLot(5));
//
//        ParkingBoyList parkingBoyList = new ParkingBoyList(
//                new ParkingBoy(new ParkingLot(parkingLotMapList)),
//                new SmartParkingBoy(new ParkingLot(parkingLotMapList)),
//                new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList)));
//
//        //when
//        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager, parkingLotList);
//        ParkingTicket newParkingTicket = parkingLotManager.askParkingBoyToWork(parkingBoyList,parkingBoyList.getParkingBoyList().get(0),car, parkingLotList);
//
//        //then
//        // Check the size of the map (Parking Boy with assigned ParkingLotList)
//        assertEquals(1, parkingLotManager.getParkingBoyParkingLotMap().size());
//        // Check if Map is empty -- expected not null
//        assertNotNull(parkingLotManager.getParkingBoyParkingLotMap());
//        // Check if the Map with Key (First Parking Boy in the list) has value
//        assertNotNull(parkingLotManager.getParkingBoyParkingLotMap().get(parkingBoyList.getParkingBoyList().get(0)));
//
//        // Expected to throw exception
//        assertNotNull(newParkingTicket);
//    }
}