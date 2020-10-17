package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.ParkingBoyBehavior;
import com.oocl.cultivation.Exception.NotYourParkingLotException;
import com.oocl.cultivation.Interface.IParkingBoy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotManager extends ParkingBoyBehavior implements IParkingBoy {
    private IParkingBoy iParkingBoy;

    private Map<IParkingBoy, List<ParkingLot>> parkingBoyParkingLotMap = new HashMap<>();

    public ParkingLotManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) {
        if(isParkingBoyParkingLot(iParkingBoy)){
            return super.park(car);
        }
        else {
            throw new NotYourParkingLotException();
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket, parkingLot.getParkingLotMapLists());
    }

    public Map<IParkingBoy, List<ParkingLot>> getParkingBoyParkingLotMap() {
        return parkingBoyParkingLotMap;
    }

    public List<IParkingBoy> addNewParkingBoy(ParkingBoyList parkingBoyList, IParkingBoy newParkingBoy){
        parkingBoyList.getParkingBoyList().add(newParkingBoy);
        return parkingBoyList.getParkingBoyList();
    }

    public Map assignParkingLotToParkingBoy(IParkingBoy iParkingBoy, List<ParkingLot> parkingLotList){
        parkingBoyParkingLotMap.put(iParkingBoy, parkingLotList);
        return parkingBoyParkingLotMap;
    }

    public Boolean isParkingBoyParkingLot(IParkingBoy iParkingBoy){
        return parkingBoyParkingLotMap.containsKey(iParkingBoy) ? true : false;
    }
}
