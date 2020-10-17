package com.oocl.cultivation;

import java.util.List;

public interface IParkCar {

    public ParkingTicket park(Car car, List<ParkingLot> parkingLotMapLists);

    public Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists);

}
