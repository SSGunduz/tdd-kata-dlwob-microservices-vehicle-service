package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
public class MockVehicleCustomerDataServiceImplTest {

    @Test
    public void Returns_Null_For_Non_Existent_Vin(){

        MockVehicleCustomerDataServiceImpl service=new MockVehicleCustomerDataServiceImpl();

        VehicleCustomerData actualVehicleCustomerData=service.getVehicleCustomerData("Non Existent Vin Code");

        assertNull(actualVehicleCustomerData);
    }

    @Test
    public void Returns_Valid_Data_For_Existent_Vin(){
        MockVehicleCustomerDataServiceImpl service=new MockVehicleCustomerDataServiceImpl();

        VehicleCustomerData actualVehicleCustomerData=service.getVehicleCustomerData("sample-vehicle-vin-code");

        assertNotNull(actualVehicleCustomerData);

        assertEquals("sample-customer-id",actualVehicleCustomerData.getCustomerId());
    }
}
