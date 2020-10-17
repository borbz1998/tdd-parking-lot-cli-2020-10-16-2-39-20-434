package com.oocl.cultivation.Behavior;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.Interface.IParkCar;
import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;

import java.util.List;

public class ParkingBoyBehavior implements IParkCar {
    private int index = 0;

    public ParkingLot parkingLot;


    public ParkingBoyBehavior(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
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
