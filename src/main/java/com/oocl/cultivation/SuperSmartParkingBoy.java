package com.oocl.cultivation;

import com.oocl.cultivation.behavior.FetchingBehavior;
import com.oocl.cultivation.behavior.SuperSmartParkingBehavior;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingEmployee {
    private SuperSmartParkingBehavior superSmartParkingBehavior;
    private FetchingBehavior fetchingBehavior;

    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        superSmartParkingBehavior = new SuperSmartParkingBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
    }

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
        superSmartParkingBehavior = new SuperSmartParkingBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
    }

    public ParkingTicket park(Car car) {
        return superSmartParkingBehavior.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return fetchingBehavior.fetch(parkingTicket);
    }

    public String getCurrentLocation(List<ParkingLot> parkingLotLists, ParkingTicket parkingTicket) {
        StringBuilder currentLocation = new StringBuilder();
        for (int i = 0; i < parkingLotLists.size(); i++) {
            if (parkingLotLists.get(i).getParkingLotMap().containsKey(parkingTicket)) {
                currentLocation.append("ParkingLot Number: ").append(i + 1);
            }
        }
        return currentLocation.toString();
    }
}
