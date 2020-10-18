package com.oocl.cultivation;

import com.oocl.cultivation.Behavior.ParkingBoyBehavior;
import com.oocl.cultivation.Interface.IParkingBoy;


public class ParkingBoy extends ParkingBoyBehavior implements IParkingBoy{

    public ParkingBoy(ParkingLot parkingLot) {
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
}
