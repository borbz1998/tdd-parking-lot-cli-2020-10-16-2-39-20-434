package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy extends ParkingBoyBehavior{

    public ParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

//    public ParkingBoy(ParkingLot parkingLot) {
//        this.parkingLot = parkingLot;
//    }
//
//    public ParkingBoy(List<ParkingLot> parkingLotList) {
//        this.parkingLotList = parkingLotList;
//    }

//    public ParkingTicket park(Car car) {
//        int index = 0;
//        for (ParkingLot parkingLotMapList: parkingLot.getParkingLotMapLists()) {
//            if(!isParkingLotMapFull(parkingLotMapList, parkingLot.getParkingLotMapLists(), index)){
//                return parkingLot.park(car, parkingLotMapList.getParkingLotMap());
//            }
//            index++;
//        }
//        throw new NoParkingLotSpaceException();
//    }


    @Override
    public ParkingTicket park(Car car) {
        return super.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }

}
