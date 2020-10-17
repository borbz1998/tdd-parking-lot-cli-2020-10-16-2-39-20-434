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

    @BeforeEach
    void setUp() {
        parkingLotMapList = new ArrayList<>();
        parkingLotMapList.add(new ParkingLot(10));
        car = new Car();
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));

        //when
        ParkingTicket parkingTicket = parkingLotManager.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
        ParkingTicket parkingTicket = parkingLotManager.park(car);

        //when
        Car fetchCar = parkingLotManager.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car secondCar = new Car();
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
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
        Car car = new Car();
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
        parkingLotManager.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //when


        //then
        assertThrows(WrongTicketException.class, () -> parkingLotManager.fetch(wrongTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
        parkingLotManager.park(car);
        ParkingTicket noTicket = null;

        //when

        //then
        assertThrows(NoTicketException.class, () -> parkingLotManager.fetch(noTicket), "Please provide your parking ticket!");
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(parkingLotMapList);
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
        ParkingTicket parkingTicket = parkingLotManager.park(car);

        //when
        Car fetchCarFirstTime = parkingLotManager.fetch(parkingTicket);
//        parkingLot.removeCarFromParkingLot(parkingTicket, parkingLotMapList);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> parkingLotManager.fetch(parkingTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotLists));
        Car secondCar = new Car();

        //when
        ParkingTicket parkingTicket = parkingLotManager.park(car);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> parkingLotManager.park(secondCar), "Not Enough Position.");
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
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);

        //when
        parkingLotManager.park(car);
        ParkingTicket parkingTicket2 = parkingLotManager.park(secondCar);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, parkingLotManager.fetch(parkingTicket2));
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
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
        List<ParkingLot> parkingLotListByManager = new ArrayList<>();

        parkingLotListByManager.add(new ParkingLot(10));
        parkingLotListByManager.add(new ParkingLot(5));

        ParkingBoyList parkingBoyList = new ParkingBoyList(
                new ParkingBoy(new ParkingLot(parkingLotMapList)),
                new SmartParkingBoy(new ParkingLot(parkingLotMapList)),
                new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList)));

        //when
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager);

        //then
        // Check the size of the map
        assertEquals(1, parkingLotManager.getParkingBoyParkingLotMap().size());
        // Check if Map is empty -- expected not null
        assertNotNull(parkingLotManager.getParkingBoyParkingLotMap());
        // Check if the Map with Key (First Parking Boy in the list) has value
        assertNotNull(parkingLotManager.getParkingBoyParkingLotMap().get(parkingBoyList.getParkingBoyList().get(0)));
    }


    @Test
    public void should_return_NotYourParkingLotException_when_parking_lot_manager_assigned_parking_boy_to_park_a_car_given_it_is_not_his_parking_lot() {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(parkingLotMapList));
        List<ParkingLot> parkingLotListByManager = new ArrayList<>();

        parkingLotListByManager.add(new ParkingLot(10));
        parkingLotListByManager.add(new ParkingLot(5));

        ParkingBoyList parkingBoyList = new ParkingBoyList(
                new ParkingBoy(new ParkingLot(parkingLotMapList)),
                new SmartParkingBoy(new ParkingLot(parkingLotMapList)),
                new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList)));

        //when
        parkingLotManager.assignParkingLotToParkingBoy(parkingBoyList.getParkingBoyList().get(0), parkingLotListByManager);


        //then
        // Check the size of the map
        assertEquals(1, parkingLotManager.getParkingBoyParkingLotMap().size());
        // Check if Map is empty -- expected not null
        assertNotNull(parkingLotManager.getParkingBoyParkingLotMap());
        // Check if the Map with Key (First Parking Boy in the list) has value
        assertNotNull(parkingLotManager.getParkingBoyParkingLotMap().get(parkingBoyList.getParkingBoyList().get(0)));

        // Expected to throw exception
        assertThrows(NotYourParkingLotException.class, () -> parkingLotManager.park(car), "Sorry Not Your Parking Lot!");
        
    }
}