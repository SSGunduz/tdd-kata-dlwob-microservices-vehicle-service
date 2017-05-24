package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
public class MockVehicleCustomerDataServiceImpl extends AbstractMockServiceImpl<VehicleCustomerData> {

    public MockVehicleCustomerDataServiceImpl() {
        super(VehicleCustomerData.class);
    }

    public VehicleCustomerData getVehicleCustomerData(String vinCode) {
        if(MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE.equals(vinCode))
            return dtoObject;
        else
            return null;
    }
}
