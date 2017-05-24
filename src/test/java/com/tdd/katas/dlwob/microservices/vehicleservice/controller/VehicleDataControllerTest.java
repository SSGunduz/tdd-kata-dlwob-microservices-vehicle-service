package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.service.VehicleDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
@RunWith(SpringRunner.class )
@WebMvcTest(controllers=VehicleDataController.class)
@WithMockUser
public class VehicleDataControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleDataService vehicleDataService;

    @Test
    public void Returns_500_when_service_error() throws Exception{
        String ANY_VIN="ANY_VIN";

        given(vehicleDataService.getVehicleData(ANY_VIN)).willThrow(new RuntimeException("MockException"));

        mockMvc.perform(
                get(VehicleDataController.URL_MAPPING+"/{vinCode}",ANY_VIN)
        ).andExpect(status().isInternalServerError());

        verify(vehicleDataService).getVehicleData(ANY_VIN);
    }

}
