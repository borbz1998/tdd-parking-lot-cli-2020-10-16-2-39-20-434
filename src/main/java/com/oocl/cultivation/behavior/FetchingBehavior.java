package com.oocl.cultivation.behavior;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.exception.NoTicketException;
import com.oocl.cultivation.exception.WrongTicketException;
import com.oocl.cultivation.interfaces.IFetchCar;

import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class FetchingBehavior implements IFetchCar {

    private List<ParkingLot> parkingLotList;

    public FetchingBehavior(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        if (isNull(parkingTicket)) {
            throw new NoTicketException();
        }
        Map correctParkingLotMap = checkIfPresentInParkingLotMap(parkingTicket, parkingLotList);
        Car fetchCar = (Car) correctParkingLotMap.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket, correctParkingLotMap);
        return fetchCar;
    }

    public Map checkIfPresentInParkingLotMap(ParkingTicket parkingTicket, List<ParkingLot> parkingLotMapLists) {
        for (ParkingLot parkingLotMapList : parkingLotMapLists) {
            if (parkingLotMapList.getParkingLotMap().containsKey(parkingTicket)) {
                return parkingLotMapList.getParkingLotMap();
            }
        }
        throw new WrongTicketException();
    }

    public void removeCarFromParkingLot(ParkingTicket parkingTicket, Map parkingLotMap) {
        parkingLotMap.remove(parkingTicket);
    }
}
