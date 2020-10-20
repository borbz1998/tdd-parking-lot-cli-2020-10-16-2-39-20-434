package com.oocl.cultivation.interfaces;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingTicket;

public interface IParkCar {
    ParkingTicket park(Car car);
}
