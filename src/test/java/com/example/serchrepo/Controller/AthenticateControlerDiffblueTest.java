package com.example.serchrepo.Controller;

import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.Role;
import com.example.serchrepo.Request.AuthenticationRequest;
import com.example.serchrepo.Request.SineUpREquest;
import com.example.serchrepo.Response.AuthenticationResponse;
import com.example.serchrepo.Service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@ContextConfiguration(classes = {AthenticateControler.class})
@ExtendWith(SpringExtension.class)
class AthenticateControlerDiffblueTest {
    @Autowired
    private AthenticateControler athenticateControler;

    @MockBean
    private AuthenticationService authenticationService;

    /**
     * Method under test: {@link AthenticateControler#register(SineUpREquest)}
     */
    @Test
    void testRegister() throws Exception {
        // Arrange
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .message("Not all who wander are lost")
                .refreshToken("ABC123")
                .role(Role.User)
                .status("Status")
                .token("ABC123")
                .build();
        when(authenticationService.register(Mockito.<SineUpREquest>any())).thenReturn(buildResult);

        SineUpREquest sineUpREquest = new SineUpREquest();
        sineUpREquest.setDepartment("Department");
        sineUpREquest.setEmail("jane.doe@example.org");
        sineUpREquest.setFirstName("Jane");
        sineUpREquest.setId(1L);
        sineUpREquest.setLastName("Doe");
        sineUpREquest.setPassword("iloveyou");
        sineUpREquest.setPhoneNo("6625550144");
        sineUpREquest.setRole(Role.User);
        sineUpREquest.setStatus(true);
        sineUpREquest.setTeamLeader("Team Leader");
        sineUpREquest.setToken("ABC123");
        sineUpREquest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(sineUpREquest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(athenticateControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"role\":\"User\",\"refreshToken\":\"ABC123\",\"accessToken\":\"ABC123\",\"token\":\"ABC123\",\"status\":\"Status\","
                                        + "\"message\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test:
     * {@link AthenticateControler#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate() throws Exception {
        // Arrange
        AuthenticationResponse buildResult = AuthenticationResponse.builder()
                .accessToken("ABC123")
                .message("Not all who wander are lost")
                .refreshToken("ABC123")
                .role(Role.User)
                .status("Status")
                .token("ABC123")
                .build();
        when(authenticationService.authenticate(Mockito.<AuthenticationRequest>any())).thenReturn(buildResult);

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setPassword("iloveyou");
        authenticationRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(authenticationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(athenticateControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"role\":\"User\",\"refreshToken\":\"ABC123\",\"accessToken\":\"ABC123\",\"token\":\"ABC123\",\"status\":\"Status\","
                                        + "\"message\":\"Not all who wander are lost\"}"));
    }
}
