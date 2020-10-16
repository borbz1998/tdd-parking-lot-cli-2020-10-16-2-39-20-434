package com.oocl.cultivation;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {

    @Test
    void should_return_a_parking_ticket_when_parking_given_a_car_to_parking_boy() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);

    }
    
    @Test
    public void should_return_correct_car_when_fetching_given_a_ticket() {
        //given    
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCar = parkingBoy.fetch(parkingTicket);
        
        //then
        assertSame(car, fetchCar);
    }
    
    @Test
    public void should_return_two_cars_when_fetching_given_two_correct_tickets() {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket1 = parkingBoy.park(firstCar);
        ParkingTicket parkingTicket2 = parkingBoy.park(secondCar);
        
        //when
        Car fetchFirstCar = parkingBoy.fetch(parkingTicket1);
        Car fetchSecondCar = parkingBoy.fetch(parkingTicket2);
        
        //then
        assertSame(firstCar, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }
    
    @Test
    public void should_return_no_car_when_fetch_given_wrong_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        ParkingTicket parkingTicket = parkingBoy.park(car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //when
        Car fetchCar = parkingBoy.fetch(wrongTicket);
        
        //then
        assertNull(fetchCar);
    }

    @Test
    public void should_return_no_car_when_fetch_given_no_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
//        ParkingTicket parkingTicket = parkingBoy.park(car);
        ParkingTicket noTicket = null;

        //when
        Car fetchCar = parkingBoy.fetch(noTicket);

        //then
        assertNull(fetchCar);
    }

    @Test
    public void should_return_no_car_when_fetch_a_car_given_used_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car fetchCarFirstTime = parkingBoy.fetch(parkingTicket);
        Car fetCarWithUsedTicket = parkingBoy.fetch(parkingTicket);

        //then
        assertSame(car, fetchCarFirstTime);
        assertNull(fetCarWithUsedTicket);

    }
}
