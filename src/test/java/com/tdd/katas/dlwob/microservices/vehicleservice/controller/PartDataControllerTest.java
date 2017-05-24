package com.tdd.katas.dlwob.microservices.vehicleservice.controller;

import com.tdd.katas.dlwob.microservices.vehicleservice.model.PartData;
import com.tdd.katas.dlwob.microservices.vehicleservice.service.PartDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Hexad GmbH on 24/05/2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PartDataController.class)
@WithMockUser
public class PartDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartDataService partDataService;

    @Test
    public void Returns_500_when_service_error_exception() throws Exception {

        final String ANY_VIN="ANY_VIN";

        given(partDataService.getPartData(ANY_VIN)).willThrow(new RuntimeException("mock Exception"));

        mockMvc.perform(
                get(PartDataController.URL_MAPPING+"/{vinCode}",ANY_VIN)
            )
            .andExpect(status().isInternalServerError());

        verify(partDataService).getPartData(ANY_VIN);

    }

    @Test
    public void Returns_404_For_Non_existent_vin() throws Exception {

        final String NON_EXISTENT_VIN="NON_EXISTENT_VIN";

        given(partDataService.getPartData(NON_EXISTENT_VIN)).willReturn(null);

        mockMvc.perform(
                get(PartDataController.URL_MAPPING+"/{vinCode}",NON_EXISTENT_VIN)
        )
                .andExpect(status().isNotFound());

        verify(partDataService).getPartData(NON_EXISTENT_VIN);

    }

    @Test
    public void Returns_Valid_Data_For_existent_vin() throws Exception {

        final String EXISTENT_VIN="EXISTENT_VIN";

        List<PartData> expectedPartDataList=new ArrayList<>();

        expectedPartDataList.add(new PartData());
        expectedPartDataList.get(0).setId("Expected id");
        expectedPartDataList.get(0).setDescription("Expected description");

        expectedPartDataList.add(new PartData());
        expectedPartDataList.get(1).setId("Expected id");
        expectedPartDataList.get(1).setDescription("Expected description");

        given(partDataService.getPartData(EXISTENT_VIN)).willReturn(expectedPartDataList);


        mockMvc.perform(
                    get(PartDataController.URL_MAPPING+"/{vinCode}",EXISTENT_VIN)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",is(expectedPartDataList.size())))
                .andExpect(jsonPath("$[0].id",is(expectedPartDataList.get(0).getId())))
                .andExpect(jsonPath("$[0].description",is(expectedPartDataList.get(0).getDescription())))
                .andExpect(jsonPath("$[1].id",is(expectedPartDataList.get(1).getId())))
                .andExpect(jsonPath("$[1].description",is(expectedPartDataList.get(1).getDescription())))
                ;

        verify(partDataService).getPartData(EXISTENT_VIN);

    }

}
