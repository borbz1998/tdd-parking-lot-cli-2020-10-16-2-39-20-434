package com.oocl.cultivation;

import com.oocl.cultivation.behavior.FetchingBehavior;
import com.oocl.cultivation.behavior.ParkingBoyBehavior;
import com.oocl.cultivation.exception.NoParkingLotSpaceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class ParkingLotManager extends ParkingEmployee {
    private ParkingBoyBehavior parkingBoyBehavior;
    private FetchingBehavior fetchingBehavior;
    private List<ParkingEmployee> parkingEmployeeList;

    private ConcurrentHashMap<ParkingEmployee, List<ParkingLot>> parkingBoyAndLotMap = new ConcurrentHashMap<>();

    public ParkingLotManager(ParkingLot parkingLot) {
        super(parkingLot);
        parkingBoyBehavior = new ParkingBoyBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
        parkingEmployeeList = new ArrayList<>();
    }

    public ParkingLotManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        parkingBoyBehavior = new ParkingBoyBehavior(this.parkingLotList);
        fetchingBehavior = new FetchingBehavior(this.parkingLotList);
        parkingEmployeeList = new ArrayList<>();
    }

    public ParkingTicket park(Car car) {
        return parkingBoyBehavior.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return fetchingBehavior.fetch(parkingTicket);
    }

    public String getCurrentLocation(List<ParkingLot> parkingLotLists, ParkingTicket parkingTicket) {
        StringBuilder currentLocation = new StringBuilder();
        for (int i = 0; i < parkingLotLists.size(); i++) {
            if (parkingLotLists.get(i).getParkingLotMap().containsKey(parkingTicket)) {
                currentLocation.append("ParkingLot Number: ").append(i + 1);
            }
        }
        return currentLocation.toString();
    }

    public List<ParkingEmployee> addNewParkingBoy(ParkingEmployee parkingEmployee) {
        parkingEmployeeList.add(parkingEmployee);
        parkingBoyAndLotMap.put(parkingEmployee, parkingEmployee.getParkingLotList());
        return parkingEmployeeList;
    }

    public ParkingTicket orderParkingBoyToPark(Car car) {
        ParkingLot orderParkingLot = getParkingLotByManager();
        return orderParkingLot.park(car, orderParkingLot.getParkingLotMap());
    }

    // TODO: 10/20/2020 Create orderParkingBoyToFetch(ParkingTicket parkingTicket)

    public ParkingLot getParkingLotByManager() {
        for (ParkingEmployee parkingEmployee : parkingEmployeeList) {
            if (parkingBoyAndLotMap.containsKey(parkingEmployee)) {
                return parkingBoyAndLotMap.get(parkingEmployee).stream()
                        .filter(parkingLot -> !parkingLot.isFull())
                        .findFirst()
                        .orElseThrow(() -> new NoParkingLotSpaceException());
            }
        }
        throw new NoParkingLotSpaceException();
    }

//    public ParkingTicket orderParkingBoyToPark(Car car) {
//        Optional <ParkingEmployee> parkingEmployee = parkingEmployeeList.stream()
//                .filter(parkingEmployee1 -> parkingEmployee1.getParkingLotList().contains(parkingLot))
//    }
//
//    public Boolean containsParkingLot(ParkingLot parkingLot) {
//        return parkingLotList.contains(parkingLot);
//    }
}
