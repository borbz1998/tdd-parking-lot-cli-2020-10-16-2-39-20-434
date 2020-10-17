package com.oocl.cultivation;

import java.util.List;

public class ParkingBoyBehavior implements IParkCar {
    private int index = 0;

    public ParkingLot parkingLot;

    private List<ParkingLot> parkingLotList;

    public ParkingBoyBehavior(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingBoyBehavior(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    @Override
    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLotMapList : parkingLot.getParkingLotMapLists()) {
            if (!isParkingLotMapFull(parkingLotMapList, parkingLot.getParkingLotMapLists(), index)) {
                return parkingLot.park(car, parkingLotMapList.getParkingLotMap());
            }
            index++;
        }
        throw new NoParkingLotSpaceException();
    }

    public Boolean isParkingLotMapFull(ParkingLot parkingLot, List<ParkingLot> parkingLotMapLists, int index) {
        return parkingLot.getParkingLotMap().size() >= parkingLotMapLists.get(index).getParkingLotCapacity();
    }
}
