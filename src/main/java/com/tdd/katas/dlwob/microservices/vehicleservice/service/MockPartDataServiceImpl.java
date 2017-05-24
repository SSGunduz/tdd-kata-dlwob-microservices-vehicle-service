package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;

import java.util.List;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
public class MockPartDataServiceImpl extends AbstractMockServiceImpl<List<PartData>>{

    protected MockPartDataServiceImpl() {
        super(new TypeReference<List<PartData>>(){});
    }

    public List<PartData> getPartData(String vinCode) {

        if("sample-vehicle-vin-code".equals(vinCode))
            return dtoObject;
        else
            return null;

    }
}