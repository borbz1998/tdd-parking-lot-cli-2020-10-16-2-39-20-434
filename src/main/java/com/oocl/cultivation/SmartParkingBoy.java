package com.oocl.cultivation;

import com.oocl.cultivation.behavior.FetchingBehavior;
import com.oocl.cultivation.behavior.SmartParkingBoyBehavior;

import java.util.List;

public class SmartParkingBoy extends ParkingEmployee {

    private SmartParkingBoyBehavior smartParkingBehavior;
    private FetchingBehavior fetchingBehavior;

    SmartParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        smartParkingBehavior = new SmartParkingBoyBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
    }

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
        smartParkingBehavior = new SmartParkingBoyBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
    }

    public ParkingTicket park(Car car) {
        return smartParkingBehavior.park(car);
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
