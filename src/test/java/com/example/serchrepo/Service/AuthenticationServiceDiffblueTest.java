package com.example.serchrepo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.serchrepo.Config.JwtService;
import com.example.serchrepo.Model.Role;
import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;
import com.example.serchrepo.Request.AuthenticationRequest;
import com.example.serchrepo.Request.SineUpREquest;
import com.example.serchrepo.Response.AuthenticationResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceDiffblueTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserrRepo userrRepo;

    /**
     * Method under test: {@link AuthenticationService#register(SineUpREquest)}
     */
    @Test
    void testRegister() {
        // Arrange
        when(userrRepo.existsUserrByEmail(Mockito.<String>any())).thenReturn(true);

        SineUpREquest request = new SineUpREquest();
        request.setDepartment("Department");
        request.setEmail("jane.doe@example.org");
        request.setFirstName("Jane");
        request.setId(1L);
        request.setLastName("Doe");
        request.setPassword("iloveyou");
        request.setPhoneNo("6625550144");
        request.setRole(Role.User);
        request.setStatus(true);
        request.setTeamLeader("Team Leader");
        request.setToken("ABC123");
        request.setUserName("janedoe");

        // Act
        AuthenticationResponse actualRegisterResult = authenticationService.register(request);

        // Assert
        verify(userrRepo).existsUserrByEmail(eq("jane.doe@example.org"));
        assertEquals("Username is already in use. Please choose a different username", actualRegisterResult.getMessage());
        assertEquals("error", actualRegisterResult.getStatus());
        assertNull(actualRegisterResult.getRole());
        assertNull(actualRegisterResult.getAccessToken());
        assertNull(actualRegisterResult.getRefreshToken());
        assertNull(actualRegisterResult.getToken());
    }

    /**
     * Method under test: {@link AuthenticationService#register(SineUpREquest)}
     */
    @Test
    void testRegister2() {
        // Arrange
        when(userrRepo.existsUserrByEmail(Mockito.<String>any())).thenReturn(false);
        when(userrRepo.existsUserrByUserName(Mockito.<String>any())).thenReturn(true);

        SineUpREquest request = new SineUpREquest();
        request.setDepartment("Department");
        request.setEmail("jane.doe@example.org");
        request.setFirstName("Jane");
        request.setId(1L);
        request.setLastName("Doe");
        request.setPassword("iloveyou");
        request.setPhoneNo("6625550144");
        request.setRole(Role.User);
        request.setStatus(true);
        request.setTeamLeader("Team Leader");
        request.setToken("ABC123");
        request.setUserName("janedoe");

        // Act
        AuthenticationResponse actualRegisterResult = authenticationService.register(request);

        // Assert
        verify(userrRepo).existsUserrByEmail(eq("jane.doe@example.org"));
        verify(userrRepo).existsUserrByUserName(eq("janedoe"));
        assertEquals("Username is already in use. Please choose a different username", actualRegisterResult.getMessage());
        assertEquals("error", actualRegisterResult.getStatus());
        assertNull(actualRegisterResult.getRole());
        assertNull(actualRegisterResult.getAccessToken());
        assertNull(actualRegisterResult.getRefreshToken());
        assertNull(actualRegisterResult.getToken());
    }

    /**
     * Method under test: {@link AuthenticationService#register(SineUpREquest)}
     */
    @Test
    void testRegister3() {
        // Arrange
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

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
        when(userrRepo.existsUserrByEmail(Mockito.<String>any())).thenReturn(false);
        when(userrRepo.existsUserrByUserName(Mockito.<String>any())).thenReturn(false);
        when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generaterefreshtoken(Mockito.<Map<String, Object>>any(), Mockito.<UserDetails>any()))
                .thenReturn("ABC123");

        SineUpREquest request = new SineUpREquest();
        request.setDepartment("Department");
        request.setEmail("jane.doe@example.org");
        request.setFirstName("Jane");
        request.setId(1L);
        request.setLastName("Doe");
        request.setPassword("iloveyou");
        request.setPhoneNo("6625550144");
        request.setRole(Role.User);
        request.setStatus(true);
        request.setTeamLeader("Team Leader");
        request.setToken("ABC123");
        request.setUserName("janedoe");

        // Act
        AuthenticationResponse actualRegisterResult = authenticationService.register(request);

        // Assert
        verify(jwtService).generateToken(isA(UserDetails.class));
        verify(jwtService).generaterefreshtoken(isA(Map.class), isA(UserDetails.class));
        verify(userrRepo).existsUserrByEmail(eq("jane.doe@example.org"));
        verify(userrRepo).existsUserrByUserName(eq("janedoe"));
        verify(userrRepo).save(isA(Userr.class));
        verify(passwordEncoder).encode(isA(CharSequence.class));
        assertEquals("ABC123", actualRegisterResult.getRefreshToken());
        assertEquals("ABC123", actualRegisterResult.getToken());
        assertEquals("Registration Successfully!", actualRegisterResult.getMessage());
        assertEquals("success", actualRegisterResult.getStatus());
        assertNull(actualRegisterResult.getRole());
        assertNull(actualRegisterResult.getAccessToken());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate() throws AuthenticationException {
        // Arrange
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

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
        when(userrRepo.findByUserName(Mockito.<String>any())).thenReturn(userr);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generaterefreshtoken(Mockito.<Map<String, Object>>any(), Mockito.<UserDetails>any()))
                .thenReturn("ABC123");

        // Act
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("janedoe", "iloveyou"));

        // Assert
        verify(jwtService).generateToken(isA(UserDetails.class));
        verify(jwtService).generaterefreshtoken(isA(Map.class), isA(UserDetails.class));
        verify(userrRepo).findByUserName(eq("janedoe"));
        verify(authenticationManager).authenticate(isA(Authentication.class));
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        assertEquals("ABC123", actualAuthenticateResult.getToken());
        assertEquals("sucsess", actualAuthenticateResult.getStatus());
        assertEquals("user rigisterd secsossfully", actualAuthenticateResult.getMessage());
        assertNull(actualAuthenticateResult.getAccessToken());
        assertEquals(Role.User, actualAuthenticateResult.getRole());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate2() throws AuthenticationException {
        // Arrange
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        Userr userr = mock(Userr.class);
        when(userr.getRole()).thenReturn(Role.User);
        when(userr.isStatus()).thenReturn(true);
        doNothing().when(userr).setDepartment(Mockito.<String>any());
        doNothing().when(userr).setEmail(Mockito.<String>any());
        doNothing().when(userr).setFirstName(Mockito.<String>any());
        doNothing().when(userr).setGitToken(Mockito.<String>any());
        doNothing().when(userr).setId(Mockito.<Long>any());
        doNothing().when(userr).setLastName(Mockito.<String>any());
        doNothing().when(userr).setPassword(Mockito.<String>any());
        doNothing().when(userr).setPhoneNo(Mockito.<String>any());
        doNothing().when(userr).setRegistrationDate(Mockito.<LocalDateTime>any());
        doNothing().when(userr).setRole(Mockito.<Role>any());
        doNothing().when(userr).setStatus(anyBoolean());
        doNothing().when(userr).setTeamLeader(Mockito.<String>any());
        doNothing().when(userr).setUserName(Mockito.<String>any());
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
        when(userrRepo.findByUserName(Mockito.<String>any())).thenReturn(userr);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generaterefreshtoken(Mockito.<Map<String, Object>>any(), Mockito.<UserDetails>any()))
                .thenReturn("ABC123");

        // Act
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("janedoe", "iloveyou"));

        // Assert
        verify(jwtService).generateToken(isA(UserDetails.class));
        verify(jwtService).generaterefreshtoken(isA(Map.class), isA(UserDetails.class));
        verify(userr).getRole();
        verify(userr).isStatus();
        verify(userr).setDepartment(eq("Department"));
        verify(userr).setEmail(eq("jane.doe@example.org"));
        verify(userr).setFirstName(eq("Jane"));
        verify(userr).setGitToken(eq("ABC123"));
        verify(userr).setId(eq(1L));
        verify(userr).setLastName(eq("Doe"));
        verify(userr).setPassword(eq("iloveyou"));
        verify(userr).setPhoneNo(eq("6625550144"));
        verify(userr).setRegistrationDate(isA(LocalDateTime.class));
        verify(userr).setRole(eq(Role.User));
        verify(userr).setStatus(eq(true));
        verify(userr).setTeamLeader(eq("Team Leader"));
        verify(userr).setUserName(eq("janedoe"));
        verify(userrRepo).findByUserName(eq("janedoe"));
        verify(authenticationManager).authenticate(isA(Authentication.class));
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        assertEquals("ABC123", actualAuthenticateResult.getToken());
        assertEquals("sucsess", actualAuthenticateResult.getStatus());
        assertEquals("user rigisterd secsossfully", actualAuthenticateResult.getMessage());
        assertNull(actualAuthenticateResult.getAccessToken());
        assertEquals(Role.User, actualAuthenticateResult.getRole());
    }

    /**
     * Method under test:
     * {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate3() throws AuthenticationException {
        // Arrange
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        Userr userr = mock(Userr.class);
        when(userr.isStatus()).thenReturn(false);
        doNothing().when(userr).setDepartment(Mockito.<String>any());
        doNothing().when(userr).setEmail(Mockito.<String>any());
        doNothing().when(userr).setFirstName(Mockito.<String>any());
        doNothing().when(userr).setGitToken(Mockito.<String>any());
        doNothing().when(userr).setId(Mockito.<Long>any());
        doNothing().when(userr).setLastName(Mockito.<String>any());
        doNothing().when(userr).setPassword(Mockito.<String>any());
        doNothing().when(userr).setPhoneNo(Mockito.<String>any());
        doNothing().when(userr).setRegistrationDate(Mockito.<LocalDateTime>any());
        doNothing().when(userr).setRole(Mockito.<Role>any());
        doNothing().when(userr).setStatus(anyBoolean());
        doNothing().when(userr).setTeamLeader(Mockito.<String>any());
        doNothing().when(userr).setUserName(Mockito.<String>any());
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
        when(userrRepo.findByUserName(Mockito.<String>any())).thenReturn(userr);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generaterefreshtoken(Mockito.<Map<String, Object>>any(), Mockito.<UserDetails>any()))
                .thenReturn("ABC123");

        // Act
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("janedoe", "iloveyou"));

        // Assert
        verify(jwtService).generateToken(isA(UserDetails.class));
        verify(jwtService).generaterefreshtoken(isA(Map.class), isA(UserDetails.class));
        verify(userr).isStatus();
        verify(userr).setDepartment(eq("Department"));
        verify(userr).setEmail(eq("jane.doe@example.org"));
        verify(userr).setFirstName(eq("Jane"));
        verify(userr).setGitToken(eq("ABC123"));
        verify(userr).setId(eq(1L));
        verify(userr).setLastName(eq("Doe"));
        verify(userr).setPassword(eq("iloveyou"));
        verify(userr).setPhoneNo(eq("6625550144"));
        verify(userr).setRegistrationDate(isA(LocalDateTime.class));
        verify(userr).setRole(eq(Role.User));
        verify(userr).setStatus(eq(true));
        verify(userr).setTeamLeader(eq("Team Leader"));
        verify(userr).setUserName(eq("janedoe"));
        verify(userrRepo).findByUserName(eq("janedoe"));
        verify(authenticationManager).authenticate(isA(Authentication.class));
        assertEquals("fuck you", actualAuthenticateResult.getToken());
        assertEquals("please contact your admin", actualAuthenticateResult.getAccessToken());
        assertEquals("stop it you are just denied", actualAuthenticateResult.getRefreshToken());
        assertEquals("you are denied", actualAuthenticateResult.getMessage());
        assertNull(actualAuthenticateResult.getRole());
        String expectedStatus = Boolean.FALSE.toString();
        assertEquals(expectedStatus, actualAuthenticateResult.getStatus());
    }
}
