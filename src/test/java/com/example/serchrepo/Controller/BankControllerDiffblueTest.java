package com.example.serchrepo.Controller;

import static org.mockito.Mockito.when;

import com.example.serchrepo.Request.BankRequest;
import com.example.serchrepo.Response.BankResponce;
import com.example.serchrepo.Service.BankService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BankController.class})
@ExtendWith(SpringExtension.class)
class BankControllerDiffblueTest {
    @Autowired
    private BankController bankController;

    @MockBean
    private BankService bankService;

    /**
     * Method under test: {@link BankController#createBank(BankRequest)}
     */
    @Test
    void testCreateBank() throws Exception {
        // Arrange
        when(bankService.createBank(Mockito.<BankRequest>any())).thenReturn(new BankResponce());

        BankRequest bankRequest = new BankRequest();
        bankRequest.setBankName("bankkk");
        bankRequest.setBankDiscription("bankdis");
//        bankRequest.setBankDiscription("Bank Discription");
//        bankRequest.setBankName("Bank Name");
        bankRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(bankRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/CreateBnak")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bankController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"bankName\":null,\"bankDiscription\":null}"));
    }

    /**
     * Method under test: {@link BankController#updateBank(BankRequest)}
     */
    @Test
    void testUpdateBank() throws Exception {
        // Arrange
        when(bankService.updateBank(Mockito.<BankRequest>any())).thenReturn(new BankResponce());

        BankRequest bankRequest = new BankRequest();
        bankRequest.setBankName("bankkk");
        bankRequest.setBankDiscription("seee");
        bankRequest.setId(1L);
        String content = (new ObjectMapper()).writeValueAsString(bankRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bankController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"bankName\":null,\"bankDiscription\":null}"));
    }

    /**
     * Method under test: {@link BankController#getall()}
     */
    @Test
    void testGetall() throws Exception {
        // Arrange
        when(bankService.getall()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/GetAllBnkimg");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(bankController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
