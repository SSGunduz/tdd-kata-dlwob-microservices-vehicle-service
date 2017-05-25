package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleInformation;
import org.springframework.stereotype.Service;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Service
public class VehicleServiceImpl implements VehicleService{

    @Override
    public VehicleInformation getVehicleInformation(String vinCode) {


        throw new UnsupportedOperationException("Not implemented yet");
    }
}
