package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockVehicleDataServiceImpl implements VehicleDataService{

    @Override
    public VehicleData getVehicleData(String vin)  {

        if(SAMPLE_VEHICLE_VIN_CODE.equals(vin))
            return sampleVehicleData;
        else
            return null;


    }

    public static final String SAMPLE_VEHICLE_VIN_CODE = "sample-vehicle-vin-code";

    public MockVehicleDataServiceImpl() {
        readJsonFile();
    }

    private VehicleData sampleVehicleData;

    private void readJsonFile() {
        ObjectMapper objectMapper = new ObjectMapper();

        String resourceName = getClass().getSimpleName() + ".json";

        InputStream resourceInputStream = null;
        try {
            resourceInputStream = getClass().getResourceAsStream(resourceName);

            VehicleData viData = objectMapper.readValue(resourceInputStream, VehicleData.class);

            sampleVehicleData = viData;
        } catch (Exception e) {
            throw new IllegalStateException("Unexpected error reading from JSON file", e);
        } finally {
            if (resourceInputStream!=null) {
                try {
                    resourceInputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
