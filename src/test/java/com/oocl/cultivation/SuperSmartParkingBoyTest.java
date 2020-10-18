package com.oocl.cultivation;

import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.Exception.NoTicketException;
import com.oocl.cultivation.Exception.WrongTicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SuperSmartParkingBoyTest2 {
    private List<ParkingLot> parkingLotMapList;
    private Car car;

    @BeforeEach
    void setUp() {
        parkingLotMapList = new ArrayList<>();
        parkingLotMapList.add(new ParkingLot(10));
//        parkingLotMapList.add(new ParkingLot(5));
        car = new Car();
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList));

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList));
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //when
        Car fetchCar = superSmartParkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car secondCar = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList));
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(car);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(secondCar);

        //when
        Car fetchFirstCar = superSmartParkingBoy.fetch(parkingTicket1);
        Car fetchSecondCar = superSmartParkingBoy.fetch(parkingTicket2);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_given_wrong_ticket() {
        //given
        Car car = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList));
        superSmartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //when


        //then
        assertThrows(WrongTicketException.class, () -> superSmartParkingBoy.fetch(wrongTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(parkingLotMapList));
        superSmartParkingBoy.park(car);
        ParkingTicket noTicket = null;

        //when

        //then
        assertThrows(NoTicketException.class, () -> superSmartParkingBoy.fetch(noTicket), "Please provide your parking ticket!");
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(parkingLotMapList);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //when
        Car fetchCarFirstTime = superSmartParkingBoy.fetch(parkingTicket);
//        parkingLot.removeCarFromParkingLot(parkingTicket, parkingLotMapList);

        //then
        assertSame(car, fetchCarFirstTime);
        assertThrows(WrongTicketException.class, () -> superSmartParkingBoy.fetch(parkingTicket), "Unrecognized Parking Ticket!");
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot(parkingLotLists));
        Car secondCar = new Car();

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        assertThrows(NoParkingLotSpaceException.class, () -> superSmartParkingBoy.park(secondCar), "Not Enough Position.");
    }

    @Test
    public void should_return_car_park_at_second_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot_with_larger_available_position_rate() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(1));
        parkingLotMapLists.add(new ParkingLot(1));

        Car secondCar = new Car();
        Car thirdCar = new Car();


        ParkingLot parkingLot = new ParkingLot(parkingLotMapLists);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);

        //when
        superSmartParkingBoy.park(car);
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(secondCar);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, superSmartParkingBoy.fetch(parkingTicket2));
    }

    @Test
    public void should_return_parking_lot_where_car_is_park_when_super_smart_parking_boy_parks_a_car_given_two_parking_lot_with_larger_available_position_rate() {
        //given
        List<ParkingLot> parkingLotMapLists = new ArrayList<>();
        parkingLotMapLists.add(new ParkingLot(2));
        parkingLotMapLists.add(new ParkingLot(5));

        Car secondCar = new Car();
        Car thirdCar = new Car();


        ParkingLot parkingLot = new ParkingLot(parkingLotMapLists);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);

        //when
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(car); // Parking Lot 1
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(secondCar); // Parking Lot 2
        ParkingTicket parkingTicket3 = superSmartParkingBoy.park(thirdCar); // Parking Lot 2
        String currentLocation = superSmartParkingBoy.getCurrentLocation(parkingLotMapLists, parkingTicket2);

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, superSmartParkingBoy.fetch(parkingTicket2));
        assertEquals("ParkingLot Number: 2", currentLocation);
    }
}