package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@Component
public class PartDataRestServiceProxy {

    public PartDataRestServiceProxy(RestTemplateBuilder restTemplateBuilder){
        restTemplateBuilder.build();
    }


    public List<PartData> getPartDataList(String vinCode) {

        throw new UnsupportedOperationException("Not Implemented");
    }
}
