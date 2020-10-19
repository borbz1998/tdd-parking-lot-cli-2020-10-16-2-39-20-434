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

class SuperSmartParkingBoyTest {

    private Car car;
    private StringBuffer currentCarLocation;
    private List<ParkingLot> parkingLotList;

    @BeforeEach
    void setUp() {
        currentCarLocation = new StringBuffer();
        car = new Car();

        ParkingLot newParkingLot = new ParkingLot(5);
        ParkingLot newParkingLot2 = new ParkingLot(5);
        parkingLotList = Arrays.asList(newParkingLot, newParkingLot2);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //when
        Car fetchCar = superSmartParkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Car secondCar = new Car();
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
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);

        //when
        superSmartParkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> superSmartParkingBoy.fetch(wrongTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);


        //when
        superSmartParkingBoy.park(car);
        ParkingTicket noTicket = null;

        //then
        NoTicketException noTicketException = assertThrows(NoTicketException.class, () -> superSmartParkingBoy.fetch(noTicket));
        assertEquals("Please provide your parking ticket!", noTicketException.getMessage());
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //when
        Car fetchCarFirstTime = superSmartParkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCarFirstTime);
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> superSmartParkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotLists);
        Car secondCar = new Car();

        //when
        superSmartParkingBoy.park(car);

        //then
        NoParkingLotSpaceException noParkingLotSpaceException = assertThrows(NoParkingLotSpaceException.class, () -> superSmartParkingBoy.park(secondCar));
        assertEquals("Not Enough Position.", noParkingLotSpaceException.getMessage());
    }


    @Test
    public void should_return_car_park_at_second_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot_same_available_position() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        Car secondCar = new Car();

//        ParkingBoyList parkingBoyList = new ParkingBoyList(superSmartParkingBoy);

        //when
        // Park at Parking Lot 1
        superSmartParkingBoy.park(car);
        // Park at Parking Lot 2
        ParkingTicket parkingTicket1 = superSmartParkingBoy.park(secondCar);
        currentCarLocation.append(superSmartParkingBoy.getCurrentLocation(parkingLotList, parkingTicket1));

        //then
        assertNotNull(parkingTicket1);
        assertSame(secondCar, superSmartParkingBoy.fetch(parkingTicket1));
        assertEquals("ParkingLot Number: 2", currentCarLocation.toString());
    }

    @Test
    public void should_return_parking_lot_where_car_is_park_when_super_smart_parking_boy_parks_a_car_given_two_parking_lot_with_larger_available_position_rate() {
        //given
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(5));

        Car secondCar = new Car();
        Car thirdCar = new Car();


        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        ParkingBoyList parkingBoyList = new ParkingBoyList(superSmartParkingBoy);

        // Park at Parking Lot 1 after parking --> 0.5
        superSmartParkingBoy.park(car);
        // Park at Parking Lot 2 after parking --> 0.8
        ParkingTicket parkingTicket2 = superSmartParkingBoy.park(secondCar);
        // Park at Parking Lot 2 after parking --> 0.6
        superSmartParkingBoy.park(thirdCar);
        currentCarLocation.append(superSmartParkingBoy.getCurrentLocation(parkingLotList, parkingTicket2));

        //then
        assertNotNull(parkingTicket2);
        assertSame(secondCar, superSmartParkingBoy.fetch(parkingTicket2));
        assertEquals("ParkingLot Number: 2", currentCarLocation.toString());
    }
}