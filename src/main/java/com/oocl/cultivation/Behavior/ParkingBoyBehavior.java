package com.oocl.cultivation.Behavior;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.Exception.NotYourParkingLotException;
import com.oocl.cultivation.Interface.IFetchCar;
import com.oocl.cultivation.Interface.IParkCar;
import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.Interface.IParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingLotList;
import com.oocl.cultivation.ParkingTicket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingBoyBehavior implements IParkCar, IFetchCar {
    private int index = 0;

    public ParkingLot parkingLot;

    public ParkingBoyBehavior(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public ParkingTicket park(Car car, IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        if (isParkingBoyAssignedToParkingLot(iParkingBoy, parkingLotList)) {
            for (ParkingLot parkingLotMapList : parkingLot.getParkingLotMapLists()) {
                if (!isParkingLotMapFull(parkingLotMapList, parkingLot.getParkingLotMapLists(), index)) {
                    return parkingLot.park(car, parkingLotMapList.getParkingLotMap());
                }
                index++;
            }
            throw new NoParkingLotSpaceException();
        } else {
            throw new NotYourParkingLotException();
        }
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket, IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        if (isParkingBoyAssignedToParkingLot(iParkingBoy, parkingLotList)) {
            return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
        }
        throw new NotYourParkingLotException();
    }

    public Boolean isParkingLotMapFull(ParkingLot parkingLot, List<ParkingLot> parkingLotMapLists, int index) {
        return parkingLot.getParkingLotMap().size() >= parkingLotMapLists.get(index).getParkingLotCapacity();
    }

    public Boolean isParkingBoyAssignedToParkingLot(IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        return parkingLotList.getParkingBoyParkingLotMap().containsKey(iParkingBoy) ? true : false;
    }
}
