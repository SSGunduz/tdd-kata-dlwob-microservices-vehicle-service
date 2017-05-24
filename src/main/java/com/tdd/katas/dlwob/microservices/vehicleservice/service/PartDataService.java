package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;

import java.util.List;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
public interface PartDataService {
    List<PartData> getPartData(String vinCode);
}
