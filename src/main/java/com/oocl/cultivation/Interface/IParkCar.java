package com.oocl.cultivation.Interface;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLotList;
import com.oocl.cultivation.ParkingTicket;

import java.util.List;

public interface IParkCar {

    public ParkingTicket park(Car car, IParkingBoy iParkingBoy, ParkingLotList parkingLotList);

}
