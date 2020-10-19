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

class ParkingBoyTest {

    private Car car;
    private List<ParkingLot> parkingLotList;

    @BeforeEach
    void setup() {
        car = new Car();

        ParkingLot newParkingLot = new ParkingLot(5);
        ParkingLot newParkingLot2 = new ParkingLot(5);
        parkingLotList = Arrays.asList(newParkingLot, newParkingLot2);
    }

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car secondCar = new Car();
        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar);

        //when
        Car fetchFirstCar = parkingBoy.fetch(parkingTicket1);
        Car fetchSecondCar = parkingBoy.fetch(parkingTicket2);

        //then
        assertSame(car, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_given_wrong_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(wrongTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoTicketException_when_fetch_given_no_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);


        //when
        parkingBoy.park(car);
        ParkingTicket noTicket = null;

        //then
        NoTicketException noTicketException = assertThrows(NoTicketException.class, () -> parkingBoy.fetch(noTicket));
        assertEquals("Please provide your parking ticket!", noTicketException.getMessage());
    }

    @Test
    public void should_return_WrongTicketException_when_fetch_a_car_given_used_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCarFirstTime = parkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCarFirstTime);
        WrongTicketException wrongTicketException = assertThrows(WrongTicketException.class, () -> parkingBoy.fetch(parkingTicket));
        assertEquals("Unrecognized Parking Ticket!", wrongTicketException.getMessage());
    }

    @Test
    public void should_return_NoParkingLotSpaceException_when_park_a_car_given_parking_lot_is_full() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotLists = new ArrayList<>();
        parkingLotLists.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotLists);
        Car secondCar = new Car();

        //when
        parkingBoy.park(car);

        //then
        NoParkingLotSpaceException noParkingLotSpaceException = assertThrows(NoParkingLotSpaceException.class, () -> parkingBoy.park(secondCar));
        assertEquals("Not Enough Position.", noParkingLotSpaceException.getMessage());
    }

    @Test
    public void should_return_car_park_at_first_parking_lot_when_parking_boy_parks_a_car_given_two_parking_lot() {
        //given
        List<ParkingLot> parkingLotMapLists;
        ParkingLot newParkingLot = new ParkingLot(1);
        ParkingLot newParkingLot2 = new ParkingLot(2);
        parkingLotMapLists = Arrays.asList(newParkingLot, newParkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotMapLists);
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car secondCar = new Car();

        //when
        ParkingTicket parkingTicket1 = parkingBoy.park(car);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar);
        StringBuffer currentCarLocation = new StringBuffer();
        currentCarLocation.append(parkingBoy.getCurrentLocation(parkingLotMapLists, parkingTicket2));

        //then
        assertNotNull(parkingTicket2);

        assertEquals("ParkingLot Number: 2", currentCarLocation.toString());
    }
}
