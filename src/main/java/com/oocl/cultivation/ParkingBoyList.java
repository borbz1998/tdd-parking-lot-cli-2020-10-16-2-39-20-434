package com.oocl.cultivation;

import com.oocl.cultivation.interfaces.IParkingBoy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingBoyList {
    private List<IParkingBoy> parkingBoyList = new ArrayList<>();

    public ParkingBoyList(IParkingBoy... parkingBoyList) {
        this.parkingBoyList.addAll(Arrays.asList(parkingBoyList));
    }

    public List<IParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }
}
