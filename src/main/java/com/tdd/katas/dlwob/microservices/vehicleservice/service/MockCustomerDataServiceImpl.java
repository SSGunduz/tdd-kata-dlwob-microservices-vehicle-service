package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockCustomerDataServiceImpl extends AbstractMockServiceImpl<CustomerData> implements CustomerDataService {

    public MockCustomerDataServiceImpl() {
        super(CustomerData.class);
    }

    @Override
    public CustomerData getCustomerData(String s) {
        if(dtoObject.getId().equals(s))
            return dtoObject;
        else
            return null;
    }


}
