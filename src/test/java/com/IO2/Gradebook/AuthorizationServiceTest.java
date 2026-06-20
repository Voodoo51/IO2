package com.IO2.Gradebook;

import com.IO2.Gradebook.dto.UserPublicData;
import com.IO2.Gradebook.exceptions.InvalidLoginException;
import com.IO2.Gradebook.misc.LoginData;
import com.IO2.Gradebook.models.SchoolClass;
import com.IO2.Gradebook.models.User;
import com.IO2.Gradebook.models.UserRole;
import com.IO2.Gradebook.repositories.UserRepository;
import com.IO2.Gradebook.services.AuthorizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthorizationService authorizationService;

    @Test
    void testValidCredentials() {
        String mail = "test@gmail.com";
        String password = "haslo";
        LoginData loginData = new LoginData(mail, password);

        int uId = 1;
        UserRole ur = new UserRole(0, "Uczen");
        SchoolClass sc = new SchoolClass(3, "ID11A");
        String name = "imie";
        String surname = "nazwisko";
        User user = new User(uId, ur, sc, loginData.getEmail(), loginData.getPassword(), name, surname);

        when(userRepository.findByEmailAndPassword(
                loginData.getEmail(),
                loginData.getPassword()
        )).thenReturn(user);

        UserPublicData result = authorizationService.login(loginData);

        assertNotNull(result);

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    void testInvalidCredentials() {
        String mail = "test@gmail.com";
        String password = "haslo";
        LoginData loginData = new LoginData(mail, password);

        when(userRepository.findByEmailAndPassword(
                anyString(),
                anyString()
        )).thenReturn(null);

        InvalidLoginException loginException = assertThrows(
                InvalidLoginException.class,
                () -> authorizationService.login(loginData)
        );

        assertEquals(
                "Invalid email or password",
                loginException.getMessage()
        );
    }
}