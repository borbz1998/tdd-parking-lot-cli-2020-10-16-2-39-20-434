package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.ParkingBoyBehavior;
import com.oocl.cultivation.Exception.NoParkingLotSpaceException;
import com.oocl.cultivation.Interface.IParkingBoy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotManager extends ParkingBoyBehavior implements IParkingBoy {

    public Map<IParkingBoy, List<ParkingLot>> parkingBoyParkingLotMap = new HashMap<>();

    public ParkingLotManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car, IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        return super.park(car, iParkingBoy, parkingLotList);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket, IParkingBoy iParkingBoy, ParkingLotList parkingLotList) {
        return super.fetch(parkingTicket, iParkingBoy, parkingLotList);
    }

    public Map<IParkingBoy, List<ParkingLot>> getParkingBoyParkingLotMap() {
        return parkingBoyParkingLotMap;
    }

    public List<IParkingBoy> addNewParkingBoy(ParkingBoyList parkingBoyList, IParkingBoy newParkingBoy) {
        parkingBoyList.getParkingBoyList().add(newParkingBoy);
        return parkingBoyList.getParkingBoyList();
    }

    public ParkingLotList assignParkingLotToParkingBoy(IParkingBoy iParkingBoy, List<ParkingLot> parkingLotLists, ParkingLotList parkingLotList) {
        parkingLotList.getParkingBoyParkingLotMap().put(iParkingBoy, parkingLotLists);
        return parkingLotList;
    }

//    public ParkingTicket askParkingBoyToWork(ParkingBoyList parkingBoyList, IParkingBoy iParkingBoy, Car car, ParkingLotList parkingLotList) {
//        parkingBoyList.getParkingBoyList().forEach(parkingBoyOne ->{
//            if(parkingBoyOne.equals(iParkingBoy)){
//                parkingBoyOne.park(car, iParkingBoy, parkingLotList);
//                parkingBoyOne.getClass().
//            }
//        });
//        throw new NoParkingLotSpaceException();
//    }

    public int getParkingBoyWithParkingLotMap(ParkingLotList parkingLotList) {
        return parkingLotList.getParkingBoyParkingLotMap().size();
    }

}
