package com.oocl.cultivation;

import com.oocl.cultivation.behavior.FetchingBehavior;
import com.oocl.cultivation.behavior.ParkingBoyBehavior;

import java.util.List;

public class ParkingBoy extends ParkingEmployee {
    private ParkingBoyBehavior parkingBoyBehavior;
    private FetchingBehavior fetchingBehavior;

    public ParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
        parkingBoyBehavior = new ParkingBoyBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        parkingBoyBehavior = new ParkingBoyBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
    }

    public ParkingTicket park(Car car) {
        return parkingBoyBehavior.park(car);
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
