package com.example.serchrepo.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.Role;
import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Request.SineUpREquest;
import com.example.serchrepo.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
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

@ContextConfiguration(classes = {AdminControler.class})
@ExtendWith(SpringExtension.class)
class AdminControlerDiffblueTest {
    @Autowired
    private AdminControler adminControler;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AdminControler#updateById(Long, SineUpREquest)}
     */
    @Test
    void testUpdateById() throws Exception {
        // Arrange
        Userr userr = new Userr();
        userr.setDepartment("Department");
        userr.setEmail("jane.doeae@example.org");
        userr.setFirstName("Jane");
        userr.setGitToken("ABC123");
        userr.setId(1L);
        userr.setLastName("Doe");
        userr.setPassword("iloveyou");
        userr.setPhoneNo("6625550144");
        userr.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        userr.setRole(Role.User);
        userr.setStatus(true);
        userr.setTeamLeader("Team Leader");
        userr.setUserName("maniga");
        when(userService.updateById(Mockito.<Long>any(), Mockito.<SineUpREquest>any())).thenReturn(userr);

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/admin/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNo\":\"6625550144\","
                                        + "\"password\":\"iloveyou\",\"teamLeader\":\"Team Leader\",\"department\":\"Department\",\"gitToken\":\"ABC123\",\"role"
                                        + "\":\"User\",\"status\":true,\"registrationDate\":[1970,1,1,0,0],\"enabled\":true,\"accountNonExpired\":true,"
                                        + "\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"User\"}],\"username\""
                                        + ":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link AdminControler#updateById(Long, SineUpREquest)}
     */
    @Test
    void testUpdateById2() throws Exception {
        // Arrange
        Userr userr = new Userr();
        userr.setDepartment("Department");
        userr.setEmail("jane.janeadaoe@example.org");
        userr.setFirstName("Jane");
        userr.setGitToken("ABC123");
        userr.setId(1L);
        userr.setLastName("Doe");
        userr.setPassword("iloveyou");
        userr.setPhoneNo("6625550144");
        userr.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        userr.setRole(Role.User);
        userr.setStatus(false);
        userr.setTeamLeader("Team Leader");
        userr.setUserName("janeadaoe");
        when(userService.updateById(Mockito.<Long>any(), Mockito.<SineUpREquest>any())).thenReturn(userr);

        SineUpREquest sineUpREquest = new SineUpREquest();
        sineUpREquest.setDepartment("Department");
        sineUpREquest.setEmail("jane.janeadaoe@example.org");
        sineUpREquest.setFirstName("Jane");
        sineUpREquest.setId(1L);
        sineUpREquest.setLastName("Doe");
        sineUpREquest.setPassword("iloveyou");
        sineUpREquest.setPhoneNo("6625550144");
        sineUpREquest.setRole(Role.User);
        sineUpREquest.setStatus(true);
        sineUpREquest.setTeamLeader("Team Leader");
        sineUpREquest.setToken("ABC123");
        sineUpREquest.setUserName("janeadaoe");
        String content = (new ObjectMapper()).writeValueAsString(sineUpREquest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/admin/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNo\":\"6625550144\","
                                        + "\"password\":\"iloveyou\",\"teamLeader\":\"Team Leader\",\"department\":\"Department\",\"gitToken\":\"ABC123\",\"role"
                                        + "\":\"User\",\"status\":false,\"registrationDate\":[1970,1,1,0,0],\"enabled\":true,\"accountNonExpired\":true,"
                                        + "\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"User\"}],\"username\""
                                        + ":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link AdminControler#suspendUser(Long)}
     */
    @Test
    void testSuspendUser() throws Exception {
        // Arrange
        doNothing().when(userService).suspendUser(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/admin/suspendUser/{userId}",
                1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User suspended successfully."));
    }

    /**
     * Method under test: {@link AdminControler#suspendUser(Long)}
     */
    @Test
    void testSuspendUser2() throws Exception {
        // Arrange
        doNothing().when(userService).suspendUser(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/admin/suspendUser/{userId}",
                1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User suspended successfully."));
    }

    /**
     * Method under test: {@link AdminControler#ActivateUser(Long)}
     */
    @Test
    void testActivateUser() throws Exception {
        // Arrange
        doNothing().when(userService).ActivateUser(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/admin/suspendUser/{userId}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User activated successfully."));
    }

    /**
     * Method under test: {@link AdminControler#ActivateUser(Long)}
     */
    @Test
    void testActivateUser2() throws Exception {
        // Arrange
        doNothing().when(userService).ActivateUser(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/admin/suspendUser/{userId}", 1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("User activated successfully."));
    }

    /**
     * Method under test: {@link AdminControler#vewuser(Long)}
     */
    @Test
    void testVewuser() throws Exception {
        // Arrange
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
        when(userService.vewuser(Mockito.<Long>any())).thenReturn(sineUpREquest);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/admin/vewuser");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"phoneNo"
                                        + "\":\"6625550144\",\"password\":\"iloveyou\",\"token\":\"ABC123\",\"role\":\"User\",\"teamLeader\":\"Team Leader\","
                                        + "\"department\":\"Department\",\"status\":true}"));
    }

    /**
     * Method under test: {@link AdminControler#getAllByEmail(String)}
     */
    @Test
    void testGetAllByEmail() throws Exception {
        // Arrange
        Userr userr = new Userr();
        userr.setDepartment("Department");
        userr.setEmail("jane.doe@example.org");
        userr.setFirstName("Jane");
        userr.setGitToken("ABC123");
        userr.setId(1L);
        userr.setLastName("Doe");
        userr.setPassword("iloveyou");
        userr.setPhoneNo("6625550144");
        userr.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        userr.setRole(Role.User);
        userr.setStatus(true);
        userr.setTeamLeader("Team Leader");
        userr.setUserName("janedoe");
        when(userService.getAllByUser(Mockito.<String>any())).thenReturn(userr);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/GetByemail")
                .param("email", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNo\":\"6625550144\","
                                        + "\"password\":\"iloveyou\",\"teamLeader\":\"Team Leader\",\"department\":\"Department\",\"gitToken\":\"ABC123\",\"role"
                                        + "\":\"User\",\"status\":true,\"registrationDate\":[1970,1,1,0,0],\"enabled\":true,\"accountNonExpired\":true,"
                                        + "\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"User\"}],\"username\""
                                        + ":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link AdminControler#getAllByEmail(String)}
     */
    @Test
    void testGetAllByEmail2() throws Exception {
        // Arrange
        Userr userr = new Userr();
        userr.setDepartment("Department");
        userr.setEmail("jane.doe@example.org");
        userr.setFirstName("Jane");
        userr.setGitToken("ABC123");
        userr.setId(1L);
        userr.setLastName("Doe");
        userr.setPassword("iloveyou");
        userr.setPhoneNo("6625550144");
        userr.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        userr.setRole(Role.User);
        userr.setStatus(false);
        userr.setTeamLeader("Team Leader");
        userr.setUserName("janedoe");
        when(userService.getAllByUser(Mockito.<String>any())).thenReturn(userr);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/GetByemail")
                .param("email", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Doe\",\"email\":\"jane.doe@example.org\",\"phoneNo\":\"6625550144\","
                                        + "\"password\":\"iloveyou\",\"teamLeader\":\"Team Leader\",\"department\":\"Department\",\"gitToken\":\"ABC123\",\"role"
                                        + "\":\"User\",\"status\":false,\"registrationDate\":[1970,1,1,0,0],\"enabled\":true,\"accountNonExpired\":true,"
                                        + "\"accountNonLocked\":true,\"credentialsNonExpired\":true,\"authorities\":[{\"authority\":\"User\"}],\"username\""
                                        + ":\"jane.doe@example.org\"}"));
    }

    /**
     * Method under test: {@link AdminControler#getallusers()}
     */
    @Test
    void testGetallusers() throws Exception {
        // Arrange
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/admin/Getalluser");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(adminControler)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
