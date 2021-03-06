package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.VehicleData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@RunWith(SpringRunner.class)
@RestClientTest(VehicleDataRestServiceProxy.class)
public class VehicleDataRestServiceProxyTest {

    @Value("${services.vehicle-data-service.url}")
    private String vehicleDataServiceUrl;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private VehicleDataRestServiceProxy proxy;

    /*
        1- If server returns 404, we get result = null
        2- If server returns a service error, an exception is thrown
        3- If server returns a client error, an exception is thrown
        4- If server returns valid data, we get back the correct data
     */

    @Test
    public void Returns_null_when_server_returns_404(){

        final String NON_EXISTENT_VIN_CODE="NON_EXISTENT_VIN_CODE";

        server.expect(requestTo(vehicleDataServiceUrl+"/"+NON_EXISTENT_VIN_CODE))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        VehicleData vehicleData=proxy.getVehicleData(NON_EXISTENT_VIN_CODE);

        assertNull(vehicleData);

        server.verify();
    }

    @Test
    public void Throws_exception_when_server_error(){

        final String ANY_VIN_CODE="ANY_VIN_CODE";

        server.expect(requestTo(vehicleDataServiceUrl+"/"+ ANY_VIN_CODE))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));

        try{
            proxy.getVehicleData(ANY_VIN_CODE);
            fail("Should have thrown an exception");
        }catch (HttpServerErrorException e){
            // Test is ok
        }

        server.verify();

    }

    @Test
    public void Throws_exception_when_client_error(){

        final String ANY_VIN_CODE="ANY_VIN_CODE";

        server.expect(requestTo(vehicleDataServiceUrl+"/"+ ANY_VIN_CODE))
                .andRespond(withStatus(HttpStatus.BAD_REQUEST));

        try{
            proxy.getVehicleData(ANY_VIN_CODE);
            fail("Should have thrown an exception");
        }catch (HttpClientErrorException e){
            // Test is ok
        }

        server.verify();

    }

    @Test
    public void Returns_valid_data_when_server_provides_valid_data(){

        final String EXISTENT_VIN_CODE="EXISTENT_VIN_CODE";

        String mockJsonResponse=
                "	{															" +
                "		\"modelId\": \"The model\",                             " +
                "		\"plateNumber\": \"The plate number\"                   " +
                "	}                                                           ";

        server.expect(requestTo(vehicleDataServiceUrl+"/"+ EXISTENT_VIN_CODE))
                .andRespond(withSuccess(mockJsonResponse, MediaType.APPLICATION_JSON_UTF8));


        VehicleData actualVehicleData=proxy.getVehicleData(EXISTENT_VIN_CODE);

        assertNotNull(actualVehicleData);
        assertEquals("The model",actualVehicleData.getModelId());
        assertEquals("The plate number",actualVehicleData.getPlateNumber());

        server.verify();

    }


}
