package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.CustomerData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Hexad GmbH on 23/05/2017.
 */
public class MockCustomerDataServiceImplTest {


    private MockCustomerDataServiceImpl service;

    @Before
    public void setUp() throws Exception {
        service = new MockCustomerDataServiceImpl();
    }

    @Test
    public void Returns_Null_For_Non_Existent_Customer_Id(){

        CustomerData actualCustomerData= service.getCustomerData("Non existent customer id");
        assertNull(actualCustomerData);
    }

    @Test
    public void Returns_Valid_Data_For_An_Existent_Customer_Id(){

        CustomerData actualCustomerData=service.getCustomerData(MockServicesConstants.SAMPLE_CUSTOMER_ID);

        assertNotNull(actualCustomerData);
        assertEquals(MockServicesConstants.SAMPLE_CUSTOMER_ID,actualCustomerData.getId());
        assertEquals("Sergio",actualCustomerData.getName());
        assertEquals("Osuna Medina",actualCustomerData.getSurnames());
    }

}
