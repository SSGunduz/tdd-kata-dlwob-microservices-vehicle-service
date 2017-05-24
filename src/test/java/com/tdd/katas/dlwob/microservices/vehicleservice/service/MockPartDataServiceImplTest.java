package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
public class MockPartDataServiceImplTest {

    private MockPartDataServiceImpl service;

    @Before
    public void setUp() throws Exception {
        service = new MockPartDataServiceImpl();
    }

    @Test
    public void Return_Null_For_Non_Existent_Vin_Code() {

        List<PartData> actualPartDataList = service.getPartData("Non existent vin code");

        assertNull(actualPartDataList);
    }

    @Test
    public void Returns_Valid_Data_For_Existent_Vin_Code(){


        List<PartData> actualPartDataList=service.getPartData("sample-vehicle-vin-code");

        assertNotNull(actualPartDataList);

        assertEquals(3,actualPartDataList.size());

        assertEquals("sample-part-1-id",actualPartDataList.get(0).getId());
        assertEquals("sample-part-1-description",actualPartDataList.get(0).getDescription());

        assertEquals("sample-part-2-id",actualPartDataList.get(1).getId());
        assertEquals("sample-part-2-description",actualPartDataList.get(1).getDescription());

        assertEquals("sample-part-3-id",actualPartDataList.get(2).getId());
        assertEquals("sample-part-3-description",actualPartDataList.get(2).getDescription());

    }

}
