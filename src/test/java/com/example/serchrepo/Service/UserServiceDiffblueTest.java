package com.example.serchrepo.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.Role;
import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;
import com.example.serchrepo.Request.SineUpREquest;
import com.example.serchrepo.Request.UserUpdateRiquest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffblueTest {
  @Autowired
  private UserService userService;

  @MockBean
  private UserrRepo userrRepo;

  /**
   * Method under test: {@link UserService#userdetalService()}
   */
  @Test
  void testUserdetalService() throws UsernameNotFoundException {
    //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

    // Arrange, Act and Assert
    assertNull((new UserService(mock(UserrRepo.class))).userdetalService().loadUserByUsername("janedoe"));
  }

  /**
   * Method under test: {@link UserService#userdetalService()}
   */
  @Test
  void testUserdetalService2() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: Missing observers.
    //   Diffblue Cover was unable to create an assertion.
    //   Add getters for the following fields or make them package-private:
    //     1.this$0

    // Arrange and Act
    userService.userdetalService();
  }

  /**
   * Method under test: {@link UserService#delateuser(Long)}
   */
  @Test
  void testDelateuser() {
    // Arrange
    doNothing().when(userrRepo).deleteById(Mockito.<Long>any());

    // Act
    userService.delateuser(1L);

    // Assert that nothing has changed
    verify(userrRepo).deleteById(eq(1L));
  }

  /**
   * Method under test: {@link UserService#delateuser(Long)}
   */
  @Test
  void testDelateuser2() {
    // Arrange
    doThrow(new RuntimeException("foo")).when(userrRepo).deleteById(Mockito.<Long>any());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.delateuser(1L));
    verify(userrRepo).deleteById(eq(1L));
  }

  /**
   * Method under test: {@link UserService#getAllUsers()}
   */
  @Test
  void testGetAllUsers() {
    // Arrange
    when(userrRepo.findAll()).thenReturn(new ArrayList<>());

    // Act
    List<SineUpREquest> actualAllUsers = userService.getAllUsers();

    // Assert
    verify(userrRepo).findAll();
    assertTrue(actualAllUsers.isEmpty());
  }

  /**
   * Method under test: {@link UserService#getAllUsers()}
   */
  @Test
  void testGetAllUsers2() {
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

    ArrayList<Userr> userrList = new ArrayList<>();
    userrList.add(userr);
    when(userrRepo.findAll()).thenReturn(userrList);

    // Act
    List<SineUpREquest> actualAllUsers = userService.getAllUsers();

    // Assert
    verify(userrRepo).findAll();
    assertEquals(1, actualAllUsers.size());
  }

  /**
   * Method under test: {@link UserService#getAllUsers()}
   */
  @Test
  void testGetAllUsers3() {
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

    Userr userr2 = new Userr();
    userr2.setDepartment("com.example.serchrepo.Model.Userr");
    userr2.setEmail("john.smith@example.org");
    userr2.setFirstName("John");
    userr2.setGitToken("Git Token");
    userr2.setId(2L);
    userr2.setLastName("Smith");
    userr2.setPassword("Password");
    userr2.setPhoneNo("8605550118");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.Admin);
    userr2.setStatus(false);
    userr2.setTeamLeader("com.example.serchrepo.Model.Userr");
    userr2.setUserName("User Name");

    ArrayList<Userr> userrList = new ArrayList<>();
    userrList.add(userr2);
    userrList.add(userr);
    when(userrRepo.findAll()).thenReturn(userrList);

    // Act
    List<SineUpREquest> actualAllUsers = userService.getAllUsers();

    // Assert
    verify(userrRepo).findAll();
    assertEquals(2, actualAllUsers.size());
  }

  /**
   * Method under test: {@link UserService#getAllUsers()}
   */
  @Test
  void testGetAllUsers4() {
    // Arrange
    when(userrRepo.findAll()).thenThrow(new RuntimeException("foo"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.getAllUsers());
    verify(userrRepo).findAll();
  }

  /**
   * Method under test: {@link UserService#getAllByUser(String)}
   */
  @Test
  void testGetAllByUser() {
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
    Optional<Userr> ofResult = Optional.of(userr);
    when(userrRepo.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

    // Act
    Userr actualAllByUser = userService.getAllByUser("jane.doe@example.org");

    // Assert
    verify(userrRepo).findByEmail(eq("jane.doe@example.org"));
    assertSame(userr, actualAllByUser);
  }

  /**
   * Method under test: {@link UserService#getAllByUser(String)}
   */
  @Test
  void testGetAllByUser2() {
    // Arrange
    Optional<Userr> emptyResult = Optional.empty();
    when(userrRepo.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.getAllByUser("jane.doe@example.org"));
    verify(userrRepo).findByEmail(eq("jane.doe@example.org"));
  }

  /**
   * Method under test: {@link UserService#getAllByUser(String)}
   */
  @Test
  void testGetAllByUser3() {
    // Arrange
    when(userrRepo.findByEmail(Mockito.<String>any())).thenThrow(new RuntimeException("this email not fond"));

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.getAllByUser("jane.doe@example.org"));
    verify(userrRepo).findByEmail(eq("jane.doe@example.org"));
  }

  /**
   * Method under test: {@link UserService#suspendUser(Long)}
   */
  @Test
  void testSuspendUser() {
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    userService.suspendUser(1L);

    // Assert
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#suspendUser(Long)}
   */
  @Test
  void testSuspendUser2() {
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
    Optional<Userr> ofResult = Optional.of(userr);
    when(userrRepo.save(Mockito.<Userr>any())).thenThrow(new RuntimeException("foo"));
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.suspendUser(1L));
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#suspendUser(Long)}
   */
  @Test
  void testSuspendUser3() {
    // Arrange
    Userr userr = mock(Userr.class);
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    userService.suspendUser(1L);

    // Assert that nothing has changed
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
    verify(userr, atLeast(1)).setStatus(anyBoolean());
    verify(userr).setTeamLeader(eq("Team Leader"));
    verify(userr).setUserName(eq("janedoe"));
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#ActivateUser(Long)}
   */
  @Test
  void testActivateUser() {
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    userService.ActivateUser(1L);

    // Assert
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#ActivateUser(Long)}
   */
  @Test
  void testActivateUser2() {
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
    Optional<Userr> ofResult = Optional.of(userr);
    when(userrRepo.save(Mockito.<Userr>any())).thenThrow(new RuntimeException("foo"));
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.ActivateUser(1L));
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#ActivateUser(Long)}
   */
  @Test
  void testActivateUser3() {
    // Arrange
    Userr userr = mock(Userr.class);
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    userService.ActivateUser(1L);

    // Assert that nothing has changed
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
    verify(userr, atLeast(1)).setStatus(eq(true));
    verify(userr).setTeamLeader(eq("Team Leader"));
    verify(userr).setUserName(eq("janedoe"));
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#updateuser(UserUpdateRiquest)}
   */
  @Test
  void testUpdateuser() {
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    Userr actualUpdateuserResult = userService.updateuser(new UserUpdateRiquest());

    // Assert
    verify(userrRepo).findById(isNull());
    verify(userrRepo).save(isA(Userr.class));
    assertSame(userr2, actualUpdateuserResult);
  }

  /**
   * Method under test: {@link UserService#updateuser(UserUpdateRiquest)}
   */
  @Test
  void testUpdateuser2() {
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
    Optional<Userr> ofResult = Optional.of(userr);
    when(userrRepo.save(Mockito.<Userr>any())).thenThrow(new RuntimeException("foo"));
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.updateuser(new UserUpdateRiquest()));
    verify(userrRepo).findById(isNull());
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#updateuser(UserUpdateRiquest)}
   */
  @Test
  void testUpdateuser3() {
    // Arrange
    Userr userr = mock(Userr.class);
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    Userr actualUpdateuserResult = userService.updateuser(new UserUpdateRiquest());

    // Assert
    verify(userr, atLeast(1)).setDepartment(Mockito.<String>any());
    verify(userr).setEmail(eq("jane.doe@example.org"));
    verify(userr, atLeast(1)).setFirstName(Mockito.<String>any());
    verify(userr).setGitToken(eq("ABC123"));
    verify(userr).setId(eq(1L));
    verify(userr, atLeast(1)).setLastName(Mockito.<String>any());
    verify(userr).setPassword(eq("iloveyou"));
    verify(userr, atLeast(1)).setPhoneNo(Mockito.<String>any());
    verify(userr).setRegistrationDate(isA(LocalDateTime.class));
    verify(userr).setRole(eq(Role.User));
    verify(userr).setStatus(eq(true));
    verify(userr, atLeast(1)).setTeamLeader(Mockito.<String>any());
    verify(userr).setUserName(eq("janedoe"));
    verify(userrRepo).findById(isNull());
    verify(userrRepo).save(isA(Userr.class));
    assertSame(userr2, actualUpdateuserResult);
  }

  /**
   * Method under test: {@link UserService#vewuser(Long)}
   */
  @Test
  void testVewuser() {
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
    Optional<Userr> ofResult = Optional.of(userr);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    SineUpREquest actualVewuserResult = userService.vewuser(1L);

    // Assert
    verify(userrRepo).findById(eq(1L));
    assertEquals("6625550144", actualVewuserResult.getPhoneNo());
    assertEquals("ABC123", actualVewuserResult.getToken());
    assertEquals("Department", actualVewuserResult.getDepartment());
    assertEquals("Doe", actualVewuserResult.getLastName());
    assertEquals("Jane", actualVewuserResult.getFirstName());
    assertEquals("Team Leader", actualVewuserResult.getTeamLeader());
    assertEquals("jane.doe@example.org", actualVewuserResult.getEmail());
    assertEquals("janedoe", actualVewuserResult.getUserName());
    assertEquals(1L, actualVewuserResult.getId().longValue());
    assertEquals(Role.User, actualVewuserResult.getRole());
    assertTrue(actualVewuserResult.isStatus());
  }

  /**
   * Method under test: {@link UserService#vewuser(Long)}
   */
  @Test
  void testVewuser2() {
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
    Userr userr = mock(Userr.class);
    when(userr.sineUpREquest()).thenReturn(sineUpREquest);
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
    Optional<Userr> ofResult = Optional.of(userr);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    // Act
    SineUpREquest actualVewuserResult = userService.vewuser(1L);

    // Assert
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
    verify(userr).sineUpREquest();
    verify(userrRepo).findById(eq(1L));
    assertSame(sineUpREquest, actualVewuserResult);
  }

  /**
   * Method under test: {@link UserService#updateById(Long, SineUpREquest)}
   */
  @Test
  void testUpdateById() {
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    SineUpREquest rEquest = new SineUpREquest();
    rEquest.setDepartment("Department");
    rEquest.setEmail("jane.doe@example.org");
    rEquest.setFirstName("Jane");
    rEquest.setId(1L);
    rEquest.setLastName("Doe");
    rEquest.setPassword("iloveyou");
    rEquest.setPhoneNo("6625550144");
    rEquest.setRole(Role.User);
    rEquest.setStatus(true);
    rEquest.setTeamLeader("Team Leader");
    rEquest.setToken("ABC123");
    rEquest.setUserName("janedoe");

    // Act
    Userr actualUpdateByIdResult = userService.updateById(1L, rEquest);

    // Assert
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
    assertSame(userr2, actualUpdateByIdResult);
  }

  /**
   * Method under test: {@link UserService#updateById(Long, SineUpREquest)}
   */
  @Test
  void testUpdateById2() {
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
    Optional<Userr> ofResult = Optional.of(userr);
    when(userrRepo.save(Mockito.<Userr>any())).thenThrow(new RuntimeException("foo"));
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    SineUpREquest rEquest = new SineUpREquest();
    rEquest.setDepartment("Department");
    rEquest.setEmail("jane.doe@example.org");
    rEquest.setFirstName("Jane");
    rEquest.setId(1L);
    rEquest.setLastName("Doe");
    rEquest.setPassword("iloveyou");
    rEquest.setPhoneNo("6625550144");
    rEquest.setRole(Role.User);
    rEquest.setStatus(true);
    rEquest.setTeamLeader("Team Leader");
    rEquest.setToken("ABC123");
    rEquest.setUserName("janedoe");

    // Act and Assert
    assertThrows(RuntimeException.class, () -> userService.updateById(1L, rEquest));
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
  }

  /**
   * Method under test: {@link UserService#updateById(Long, SineUpREquest)}
   */
  @Test
  void testUpdateById3() {
    // Arrange
    Userr userr = mock(Userr.class);
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
    Optional<Userr> ofResult = Optional.of(userr);

    Userr userr2 = new Userr();
    userr2.setDepartment("Department");
    userr2.setEmail("jane.doe@example.org");
    userr2.setFirstName("Jane");
    userr2.setGitToken("ABC123");
    userr2.setId(1L);
    userr2.setLastName("Doe");
    userr2.setPassword("iloveyou");
    userr2.setPhoneNo("6625550144");
    userr2.setRegistrationDate(LocalDate.of(1970, 1, 1).atStartOfDay());
    userr2.setRole(Role.User);
    userr2.setStatus(true);
    userr2.setTeamLeader("Team Leader");
    userr2.setUserName("janedoe");
    when(userrRepo.save(Mockito.<Userr>any())).thenReturn(userr2);
    when(userrRepo.findById(Mockito.<Long>any())).thenReturn(ofResult);

    SineUpREquest rEquest = new SineUpREquest();
    rEquest.setDepartment("Department");
    rEquest.setEmail("jane.doe@example.org");
    rEquest.setFirstName("Jane");
    rEquest.setId(1L);
    rEquest.setLastName("Doe");
    rEquest.setPassword("iloveyou");
    rEquest.setPhoneNo("6625550144");
    rEquest.setRole(Role.User);
    rEquest.setStatus(true);
    rEquest.setTeamLeader("Team Leader");
    rEquest.setToken("ABC123");
    rEquest.setUserName("janedoe");

    // Act
    Userr actualUpdateByIdResult = userService.updateById(1L, rEquest);

    // Assert
    verify(userr, atLeast(1)).setDepartment(eq("Department"));
    verify(userr).setEmail(eq("jane.doe@example.org"));
    verify(userr, atLeast(1)).setFirstName(eq("Jane"));
    verify(userr).setGitToken(eq("ABC123"));
    verify(userr).setId(eq(1L));
    verify(userr, atLeast(1)).setLastName(eq("Doe"));
    verify(userr).setPassword(eq("iloveyou"));
    verify(userr, atLeast(1)).setPhoneNo(eq("6625550144"));
    verify(userr).setRegistrationDate(isA(LocalDateTime.class));
    verify(userr).setRole(eq(Role.User));
    verify(userr, atLeast(1)).setStatus(eq(true));
    verify(userr, atLeast(1)).setTeamLeader(eq("Team Leader"));
    verify(userr).setUserName(eq("janedoe"));
    verify(userrRepo).findById(eq(1L));
    verify(userrRepo).save(isA(Userr.class));
    assertSame(userr2, actualUpdateByIdResult);
  }
}
