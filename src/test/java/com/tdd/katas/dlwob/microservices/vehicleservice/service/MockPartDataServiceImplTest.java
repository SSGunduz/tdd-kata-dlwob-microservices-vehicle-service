package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNull;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
public class MockPartDataServiceImplTest {

    @Test
    public void Return_Null_For_Non_Existent_Vin_Code() {

        MockPartDataServiceImpl service = new MockPartDataServiceImpl();

        List<PartData> actualPartDataList = service.getPartData("Non existent vin code");

        assertNull(actualPartDataList);
    }


}
