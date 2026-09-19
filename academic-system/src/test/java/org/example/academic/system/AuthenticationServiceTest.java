package org.example.academic.system;

import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.UserRepository;
import org.example.academic.system.security.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/** Testes de autenticacao (US-2386) usando Mockito para o repositorio. */
@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    private AuthenticationService service;

    @BeforeEach
    void setUp() {
        service = new AuthenticationService(userRepository);
    }

    @Test
    void validCredentialsAuthenticateSuccessfully() {
        when(userRepository.findByUsername("admin"))
                .thenReturn(Optional.of(new User("admin", "admin123", Role.ADMIN)));

        User authenticated = service.authenticate("admin", "admin123");

        assertEquals("admin", authenticated.getUsername());
        assertEquals(Role.ADMIN, authenticated.getRole());
    }

    @Test
    void invalidUsernameThrowsAuthenticationException() {
        when(userRepository.findByUsername("fantasma")).thenReturn(Optional.empty());

        assertThrows(AuthenticationException.class,
                () -> service.authenticate("fantasma", "qualquer"));
    }

    @Test
    void invalidPasswordThrowsAuthenticationException() {
        when(userRepository.findByUsername("admin"))
                .thenReturn(Optional.of(new User("admin", "admin123", Role.ADMIN)));

        assertThrows(AuthenticationException.class,
                () -> service.authenticate("admin", "senhaErrada"));
    }
}
