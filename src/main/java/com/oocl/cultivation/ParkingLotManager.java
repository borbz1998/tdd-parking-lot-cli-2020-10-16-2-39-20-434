package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.ParkingBoyBehavior;
import com.oocl.cultivation.Exception.NotYourParkingLotException;
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

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }

    public Map<IParkingBoy, List<ParkingLot>> getParkingBoyParkingLotMap() {
        return parkingBoyParkingLotMap;
    }

    public List<IParkingBoy> addNewParkingBoy(ParkingBoyList parkingBoyList, IParkingBoy newParkingBoy) {
        parkingBoyList.getParkingBoyList().add(newParkingBoy);
        return parkingBoyList.getParkingBoyList();
    }

    public ParkingLotList assignParkingLotToParkingBoy(IParkingBoy iParkingBoy, List<ParkingLot> parkingLotLists, ParkingLotList parkingLotList) {
//        parkingBoyParkingLotMap.put(iParkingBoy, parkingLotList);
        parkingLotList.getParkingBoyParkingLotMap().put(iParkingBoy,parkingLotLists);
        return parkingLotList;
    }

//    public Boolean isParkingBoyAssignedToParkingLot(IParkingBoy iParkingBoy) {
//        return parkingBoyParkingLotMap.containsKey(iParkingBoy) ? true : false;
//    }

//    public ParkingTicket askParkingBoyToWork(ParkingBoyList parkingBoyList, int index, Car car) {
//        return parkingBoyList.getParkingBoyList().get(index).equals(parkingBoy) ? parkingBoy.park(car) : null;
//    }

    public int getParkingBoyWithParkingLotMap(ParkingLotList parkingLotList){
        return parkingLotList.getParkingBoyParkingLotMap().size();
    }

}
