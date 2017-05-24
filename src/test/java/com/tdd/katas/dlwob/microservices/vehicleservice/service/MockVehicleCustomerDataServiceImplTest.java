package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
public class MockVehicleCustomerDataServiceImplTest {

    @Test
    public void Returne_Null_For_Non_Existent_Vin(){

        MockVehicleCustomerDataServiceImpl service=new MockVehicleCustomerDataServiceImpl();

        VehicleCustomerData actualVehicleCustomerData=service.getVehicleCustomerData("Non Existent Vin Code");

        assertNull(actualVehicleCustomerData);
    }
}
