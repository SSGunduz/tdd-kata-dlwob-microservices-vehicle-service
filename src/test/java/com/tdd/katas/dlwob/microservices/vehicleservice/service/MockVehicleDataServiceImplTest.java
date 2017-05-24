package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockVehicleDataServiceImplTest {

    private MockVehicleDataServiceImpl service;

    @Before
    public void setup(){

        service=new MockVehicleDataServiceImpl();
    }


    @Test
    public void  Return_Valid_Data_For_An_Existent_Vehicle(){

        VehicleData actualVehicleData= service.getVehicleData(MockServicesConstants.SAMPLE_VEHICLE_VIN_CODE);

        assertNotNull(actualVehicleData);
        assertEquals("sample-vehicle-model-id",actualVehicleData.getModelId());
        assertEquals("sample-vehicle-plate-number",actualVehicleData.getPlateNumber());

    }

    @Test
    public void Return_Null_For_Non_Existent_Vin_Code(){

        VehicleData actualVehicleData=service.getVehicleData("sample-not-exist");
        assertNull(actualVehicleData);
    }




}
