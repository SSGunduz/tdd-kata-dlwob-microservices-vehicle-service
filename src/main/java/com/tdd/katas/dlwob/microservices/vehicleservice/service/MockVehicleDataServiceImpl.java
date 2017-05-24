package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockVehicleDataServiceImpl extends AbstractMockServiceImpl<VehicleData> implements VehicleDataService{

    @Override
    public VehicleData getVehicleData(String vin)  {

        if(MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE.equals(vin))
            return dtoObject;
        else
            return null;

    }



    public MockVehicleDataServiceImpl() {
           super(VehicleData.class);
    }

}
