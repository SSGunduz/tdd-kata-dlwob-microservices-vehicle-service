package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockVehicleDataServiceImplTest {

    @Test
    public void  Return_Valid_Data_For_An_Existent_Vehicle(){

        MockVehicleDataServiceImpl service=new MockVehicleDataServiceImpl();

        VehicleData actualVehicleData= service.getVehicleData("sample-vehicle-vin-code");

        assertNotNull(actualVehicleData);
        assertEquals("sample-vehicle-model-id",actualVehicleData.getModelId());
        assertEquals("sample-vehicle-plate-number",actualVehicleData.getPlateNumber());

    }



}
