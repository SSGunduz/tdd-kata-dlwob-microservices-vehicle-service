package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleCustomerData;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
public interface VehicleCustomerDataService {
    VehicleCustomerData getVehicleCustomerData(String vinCode);
}
