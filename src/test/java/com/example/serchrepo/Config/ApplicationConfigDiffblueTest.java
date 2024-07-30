package com.example.serchrepo.Config;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.serchrepo.Model.Role;
import com.example.serchrepo.Model.Userr;
import com.example.serchrepo.Repository.UserrRepo;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicationConfig.class, AuthenticationConfiguration.class})
@ExtendWith(SpringExtension.class)
class ApplicationConfigDiffblueTest {
    @Autowired
    private ApplicationConfig applicationConfig;

    @MockBean
    private UserrRepo userrRepo;

    /**
     * Method under test: {@link ApplicationConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService() throws UsernameNotFoundException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

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
        UserrRepo userrRepo = mock(UserrRepo.class);
        when(userrRepo.findByUserName(Mockito.<String>any())).thenReturn(userr);

        // Act
        UserDetails actualLoadUserByUsernameResult = (new ApplicationConfig(userrRepo)).userDetailsService()
                .loadUserByUsername("janedoe");

        // Assert
        verify(userrRepo).findByUserName(eq("janedoe"));
        assertSame(userr, actualLoadUserByUsernameResult);
    }

    /**
     * Method under test: {@link ApplicationConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService2() throws UsernameNotFoundException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserrRepo userrRepo = mock(UserrRepo.class);
        when(userrRepo.findByUserName(Mockito.<String>any())).thenThrow(new UsernameNotFoundException("Msg"));

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> (new ApplicationConfig(userrRepo)).userDetailsService().loadUserByUsername("janedoe"));
        verify(userrRepo).findByUserName(eq("janedoe"));
    }

    /**
     * Method under test: {@link ApplicationConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     0x00000008017ae930.arg$1

        // Arrange and Act
        applicationConfig.userDetailsService();
    }

    /**
     * Method under test: {@link ApplicationConfig#athenticationProvider()}
     */
    @Test
    void testAthenticationProvider() {
        // Arrange, Act and Assert
        assertTrue(((DaoAuthenticationProvider) applicationConfig.athenticationProvider())
                .getUserCache() instanceof NullUserCache);
    }

    /**
     * Method under test: {@link ApplicationConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        // Arrange, Act and Assert
        assertTrue(applicationConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

    /**
     * Method under test:
     * {@link ApplicationConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager() throws Exception {
        // Arrange, Act and Assert
        assertTrue(
                ((ProviderManager) applicationConfig.authenticationManager(new AuthenticationConfiguration())).getProviders()
                        .get(0) instanceof DaoAuthenticationProvider);
    }

    /**
     * Method under test:
     * {@link ApplicationConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager2() throws Exception {
        // Arrange
        AuthenticationConfiguration config = new AuthenticationConfiguration();
        config.setApplicationContext(mock(AnnotationConfigApplicationContext.class));

        // Act and Assert
        assertTrue(((ProviderManager) applicationConfig.authenticationManager(config)).getProviders()
                .get(0) instanceof DaoAuthenticationProvider);
    }
}
