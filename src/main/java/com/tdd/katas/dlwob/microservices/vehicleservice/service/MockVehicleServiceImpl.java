package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.springframework.stereotype.Service;

@Service
public class MockVehicleServiceImpl extends AbstractMockServiceImpl<VehicleInformation> implements VehicleService {


    public MockVehicleServiceImpl() {
        super(VehicleInformation.class);
    }

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {
        if (dtoObject.getVin().equals(vinCode)) {
            return dtoObject;
        }
        else {
            return null;
        }
    }
}
