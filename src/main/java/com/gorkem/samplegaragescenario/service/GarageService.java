package com.gorkem.samplegaragescenario.service;

import com.gorkem.samplegaragescenario.model.concretes.Garage;
import com.gorkem.samplegaragescenario.model.concretes.Vehicle;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GarageService {

    //For in memory
    private final Garage garage = new Garage();

    public GarageService() {
        for (int i = 0; i < garage.getCapacity(); i++) {
            garage.getInMemoryGarage().put(i, null);
        }
    }

    public Garage showTheGarage() {
        return this.garage;
    }

    public void quitVehicleFromGarage(Map<Integer, Vehicle> garage, String vehiclePlaque) throws Exception {
        for (Map.Entry<Integer, Vehicle> entry : garage.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getPlaque().equals(vehiclePlaque)) {
                    garage.replace(entry.getKey(), null);

                }
            }
        }
    }

    public String addToVehicleTotalProcess(Map<Integer, Vehicle> garage, Vehicle vehicle) {
        String result = "";
        int lastIndex = garage.size() - 1;

        int firstEmptyIndex = findTheFirstEmptyIndex(garage);

        boolean value = false;

        while (!value) {
            if (isThisFirstIndex(firstEmptyIndex)) {
                if (isThereAnyVehicleAfterLocation(garage, firstEmptyIndex)) {
                    firstEmptyIndex = findTheNextEmptyIndex(garage, firstEmptyIndex);
                } else {
                    if (canMyVehicleSettleStart(garage, vehicle, firstEmptyIndex) != -1) {
                        firstEmptyIndex = -1;
                        addToMultipleVehicleForTotalProcess(garage, firstEmptyIndex, vehicle);
                        result = findTheSlotNumber(vehicle.getSize());
                        value = true;
                    } else {
                        firstEmptyIndex = findTheNextEmptyIndex(garage, firstEmptyIndex);
                    }
                }
            } else {
                if (isThereAnyAfterLocation(firstEmptyIndex)) {
                    if (isThereAnyVehicleAfterLocation(garage, firstEmptyIndex)) {
                        firstEmptyIndex = findTheNextEmptyIndex(garage, firstEmptyIndex);
                    } else {
                        if (canMyVehicleSettleHere(garage, vehicle, firstEmptyIndex) != -1) {
                            addToMultipleVehicleForTotalProcess(garage, firstEmptyIndex, vehicle);
                            result = findTheSlotNumber(vehicle.getSize());
                            value = true;
                        } else {
                            if (isTheEmptyTheLastIndex(garage, lastIndex)) {
                                String carToSettle = whichVehicleSettlesToLastIndex(firstEmptyIndex, lastIndex);
                                if (!isNullOrNot(carToSettle)) {
                                    if (typeControl(vehicle, carToSettle)) {
                                        addToMultipleVehicleForTotalProcess(garage, firstEmptyIndex, vehicle);
                                        result = findTheSlotNumber(vehicle.getSize());
                                        value = true;
                                    } else {
                                        firstEmptyIndex = findTheNextEmptyIndex(garage, firstEmptyIndex);
                                    }
                                } else {
                                    firstEmptyIndex = findTheNextEmptyIndex(garage, firstEmptyIndex);
                                }
                            } else {//zaten araba vardır
                                result = "Garage is full.";
                            }
                        }
                    }
                } else {
                    result = "Garage is full.";
                    value = true;
                }
            }
        }
        return result;
    }

    public boolean checkTheGarage(String result) {
        return (result.equals("Garage is full."));
    }

    private static void addToMultipleVehicleForTotalProcess(Map<Integer, Vehicle> garage, int firstEmptyIndex, Vehicle vehicle) {
        if (vehicle.getSize() == 1) {
            garage.put(firstEmptyIndex + 1, vehicle);
        } else if (vehicle.getSize() == 2) {
            for (int i = 1; i <= 2; i++) {
                garage.put(firstEmptyIndex + i, vehicle);
            }
        } else if (vehicle.getSize() == 4) {
            for (int i = 1; i <= 4; i++) {
                garage.put(firstEmptyIndex + i, vehicle);
            }
        }
    }

    private static int canMyVehicleSettleStart(Map<Integer, Vehicle> garage, Vehicle vehicle, int firstEmptyIndex) {
        if (typeControl(vehicle, "Car")) {
            if (canTheCarSettleToTheStart(firstEmptyIndex, garage)) {
                return vehicle.getSize();
            }
        } else if (typeControl(vehicle, "Jeep")) {
            if (canTheJeepSettleToTheStart(firstEmptyIndex, garage)) {
                return vehicle.getSize();
            }
        } else if (typeControl(vehicle, "Truck")) {
            if (canTheTruckSettleToTheStart(firstEmptyIndex, garage)) {
                return vehicle.getSize();
            }
        }
        return -1;
    }

    private static int canMyVehicleSettleHere(Map<Integer, Vehicle> garage, Vehicle vehicle, int firstEmptyIndex) {
        if (typeControl(vehicle, "Car")) {
            if (canTheCarFitInTheGarage(firstEmptyIndex, garage)) {
                return vehicle.getSize();
            }
        } else if (typeControl(vehicle, "Jeep")) {
            if (canTheJeepFitInTheGarage(firstEmptyIndex, garage)) {
                return vehicle.getSize();
            }
        } else if (typeControl(vehicle, "Truck")) {
            if (canTheTruckFitInTheGarage(firstEmptyIndex, garage)) {
                return vehicle.getSize();
            }
        }
        return -1;
    }

    private static int findTheNextEmptyIndex(Map<Integer, Vehicle> garage, int firstEmptyIndex) {
        for (Map.Entry<Integer, Vehicle> entry : garage.entrySet()) {
            if (entry.getValue() == null) {
                if (entry.getKey() < firstEmptyIndex || entry.getKey() == firstEmptyIndex) {
                    continue;
                }
                return entry.getKey();
            }
        }
        return -1;
    }

    private static boolean typeControl(Vehicle vehicle, String vehicleType) {
        try {
            if (vehicle.getType().equals(vehicleType)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private static boolean isThereAnyAfterLocation(int firstFindEmptyIndex) {
        if (firstFindEmptyIndex + 1 >= 10) {
            return false;
        }
        return true;
    }

    private static boolean isThereAnyVehicleAfterLocation(Map<Integer, Vehicle> garage, int firstFindEmptyIndex) {
        if (isThereAnyAfterLocation(firstFindEmptyIndex)) {
            if (garage.get(firstFindEmptyIndex + 1) != null) {
                return true;
            }
        }
        return false;
    }

    private static boolean isTheEmptyTheLastIndex(Map<Integer, Vehicle> garage, int lastIndex) {
        return (garage.get(lastIndex) == null);
    }

    private static String whichVehicleSettlesToLastIndex(int firstFindEmptyIndex, int lastIndex) {
        int value = lastIndex - firstFindEmptyIndex;

        if (value == 1) {
            return "Car";
        }
        if (value == 2) {
            return "Jeep";
        }
        if (value == 4) {
            return "Truck";
        }
        return "Null";
    }

    private static boolean isNullOrNot(String value) {
        return value.equals("Null");
    }

    private static boolean isThisFirstIndex(int firstFindEmptyIndex) {
        return (firstFindEmptyIndex == 0);
    }

    private static int findTheFirstEmptyIndex(Map<Integer, Vehicle> garage) {
        for (Map.Entry<Integer, Vehicle> entry : garage.entrySet()) {
            if (entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return -1;
    }

    private static boolean canTheCarSettleToTheStart(int firstFindEmptyIndex, Map<Integer, Vehicle> garage) {
        return (garage.get(firstFindEmptyIndex + 1) == null);
    }

    private static boolean canTheJeepSettleToTheStart(int firstFindEmptyIndex, Map<Integer, Vehicle> garage) {
        return (garage.get(firstFindEmptyIndex + 2) == null);
    }

    private static boolean canTheTruckSettleToTheStart(int firstFindEmptyIndex, Map<Integer, Vehicle> garage) {
        return (garage.get(firstFindEmptyIndex + 4) == null);
    }

    private static boolean canTheCarFitInTheGarage(int firstFindEmptyIndex, Map<Integer, Vehicle> garage) {
        return (garage.get(firstFindEmptyIndex + 2) == null);
    }

    private static boolean canTheJeepFitInTheGarage(int firstFindEmptyIndex, Map<Integer, Vehicle> garage) {
        return (garage.get(firstFindEmptyIndex + 3) == null);
    }

    private static boolean canTheTruckFitInTheGarage(int firstFindEmptyIndex, Map<Integer, Vehicle> garage) {
        return (garage.get(firstFindEmptyIndex + 5) == null);
    }

    private static String findTheSlotNumber(int vehicleSize) {
        if (vehicleSize == 2 || vehicleSize == 4) {
            return "Allocated " + vehicleSize + " slots.";
        }
        return "Allocated " + vehicleSize + " slot.";
    }
}
