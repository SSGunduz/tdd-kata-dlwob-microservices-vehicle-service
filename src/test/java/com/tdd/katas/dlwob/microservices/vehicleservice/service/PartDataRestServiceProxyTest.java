package com.tdd.katas.dlwob.microservices.vehicleservice.service;

import com.tdd.katas.dlwob.microservices.vehicleservice.controller.PartDataController;
import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by Hexad GmbH on 25/05/2017.
 */
@RunWith(SpringRunner.class)
@RestClientTest(PartDataRestServiceProxy.class)
public class PartDataRestServiceProxyTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private PartDataRestServiceProxy proxy;

    /*
        1- If server returns 404, we get result = null
        2- If server returns a service error, an exception is thrown
        3- If server returns a client error, an exception is thrown
        4- If server returns valid data, we get back the correct data
     */

    @Test
    public void Returns_null_when_server_returns_404(){

        final String NON_EXISTENT_VIN_CODE="NON_EXISTENT_VIN_CODE";

        server.expect(requestTo(PartDataController.URL_MAPPING+"/"+NON_EXISTENT_VIN_CODE))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        List<PartData> actualPartDataList=proxy.getPartDataList(NON_EXISTENT_VIN_CODE);

        assertNull(actualPartDataList);

        server.verify();
    }

    @Test
    public void Throws_exception_when_server_error(){

        final String ANY_VIN_CODE="ANY_VIN_CODE";

        server.expect(requestTo(PartDataController.URL_MAPPING+"/"+ ANY_VIN_CODE))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));

        try{
            proxy.getPartDataList(ANY_VIN_CODE);
            fail("Should have thrown an exception");
        }catch (HttpServerErrorException e){
            // Test is ok
        }

        server.verify();

    }

}
