package com.oocl.cultivation;

import java.util.List;

public interface IFetchCar {

    public Car fetch(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists);
}
