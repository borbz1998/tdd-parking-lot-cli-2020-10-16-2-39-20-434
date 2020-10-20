package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ParkingLotTest {

    @Test
    void should_return_a_parking_lot_is_full_when_parking_boy_parks_car_given_one_available_position_left() {
        //given
        Car car = new Car();
        ParkingLot newParkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = Arrays.asList(newParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertTrue(newParkingLot.isFull());
    }

    @Test
    void should_return_car_is_present_when_parking_boy_fetch_car_given_parking_lot() {
        //given
        Car car = new Car();
        ParkingLot newParkingLot = new ParkingLot(1);

        List<ParkingLot> parkingLotList = Arrays.asList(newParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertTrue(newParkingLot.isCarPresent(parkingTicket));
    }

    @Test
    void should_parking_lot_with_more_available_position_given_two_parking_lot() {
        //given
        ParkingLot newParkingLot = new ParkingLot(4);
        ParkingLot newParkingLot2 = new ParkingLot(5);

        //when
        List<ParkingLot> parkingLotList = Arrays.asList(newParkingLot, newParkingLot2);

        //then
        assertTrue(newParkingLot.getTheParkingLotWithMoreAvailablePosition() <
                newParkingLot2.getTheParkingLotWithMoreAvailablePosition());
    }

    @Test
    void should_parking_lot_with_more_available_position_rate_when_parking_boy_parks_car_given_two_parking_lot() {
        //given
        Car car = new Car();
        ParkingLot newParkingLot = new ParkingLot(5);
        ParkingLot newParkingLot2 = new ParkingLot(5);

        List<ParkingLot> parkingLotList = Arrays.asList(newParkingLot, newParkingLot2);

        //when
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);


        //then
        assertTrue(newParkingLot.getTheParkingLotWithMoreAvailablePositionRate() <
                newParkingLot2.getTheParkingLotWithMoreAvailablePositionRate());
    }

    @Test
    void should_parking_lot_with_more_available_position_when_parking_boy_parks_car_given_two_parking_lot() {
        //given
        Car car = new Car();
        ParkingLot newParkingLot = new ParkingLot(5);
        ParkingLot newParkingLot2 = new ParkingLot(5);

        List<ParkingLot> parkingLotList = Arrays.asList(newParkingLot, newParkingLot2);

        //when
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotList);
        ParkingTicket parkingTicket = smartParkingBoy.park(car);


        //then
        assertTrue(newParkingLot.getTheParkingLotWithMoreAvailablePosition() <
                newParkingLot2.getTheParkingLotWithMoreAvailablePosition());
    }
}