package com.oocl.cultivation.Interface;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingLotList;
import com.oocl.cultivation.ParkingTicket;

import java.util.List;

public interface IFetchCar {

    public Car fetch(ParkingTicket parkingTicket, IParkingBoy iParkingBoy, ParkingLotList parkingLotList);
}
