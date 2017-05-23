package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockVehicleDataServiceImpl implements VehicleDataService{


    @Override
    public VehicleData getVehicleData(String vin)  {
            throw new UnsupportedOperationException("Will be implemented");
    }


}
