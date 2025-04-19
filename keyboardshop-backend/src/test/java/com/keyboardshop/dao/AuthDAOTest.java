// package com.keyboardshop.backend.dao;

// import com.keyboardshop.backend.dto.AuthenticationDTO;
// import com.keyboardshop.backend.dto.LoginResponseDTO;
// import com.keyboardshop.backend.models.CustomUser;
// import com.keyboardshop.backend.repository.CustomUserRepository;
// import com.keyboardshop.backend.services.ValidationService;
// import com.keyboardshop.backend.config.JWTUtil;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.server.ResponseStatusException;
// import org.springframework.http.HttpStatus;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// @ExtendWith(MockitoExtension.class)
// public class AuthDAOTest
// {
//     @Mock
//     private CustomUserRepository customUserRepository;

//     @Mock
//     private PasswordEncoder passwordEncoder;

//     @Mock
//     private ValidationService validationService;

//     @Mock
//     private AuthenticationManager authManager;

//     @Mock
//     private JWTUtil jwtUtil;

//     @InjectMocks
//     private AuthDAO authDAO;

//     @Test
//     public void testRegisterSuccess()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("password123");

//         CustomUser customUser = new CustomUser()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("encodedPassword");

//         when(this.validationService.isValidEmail(anyString())).thenReturn(true);
//         when(this.validationService.isValidPassword(anyString())).thenReturn(true);
//         when(this.customUserRepository.findByEmail(anyString())).thenReturn(null);
//         when(this.passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
//         when(this.jwtUtil.generateToken(anyString())).thenReturn("jwtToken");

//         LoginResponseDTO response = this.authDAO.register(authDTO);

//         assertNotNull(response);
//         assertEquals("user@gmail.com", response.getEmail());
//         assertEquals("jwtToken", response.getToken());
//         verify(this.customUserRepository, times(1)).save(any(CustomUser.class));
//     }

//     @Test
//     public void testRegisterInvalidEmail()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("invalid-email")
//             .setPassword("password123");

//         when(this.validationService.isValidEmail(anyString())).thenReturn(false);

//         ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//             this.authDAO.register(authDTO);
//         });

//         assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
//         assertEquals("No valid email provided", exception.getReason());
//     }

//     @Test
//     public void testRegisterInvalidPassword()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("short");

//         when(this.validationService.isValidEmail(anyString())).thenReturn(true);
//         when(this.validationService.isValidPassword(anyString())).thenReturn(false);

//         ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//             this.authDAO.register(authDTO);
//         });

//         assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
//         assertEquals("No valid password provided", exception.getReason());
//     }

//     @Test
//     public void testRegisterMissingCredentials()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO();

//         ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//             this.authDAO.register(authDTO);
//         });

//         assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
//         assertEquals("No valid email provided", exception.getReason());
//     }

//     @Test
//     public void testRegisterMissingFirstAndLastName()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO()
//             .setEmail("user@gmail.com")
//             .setPassword("password123");

//         ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//             this.authDAO.register(authDTO);
//         });

//         verify(this.customUserRepository, never()).save(any());
//     }

//     @Test
//     public void testRegisterEmailAlreadyExists()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("password123");

//         CustomUser existingUser = new CustomUser()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("encodedPassword");

//         when(this.validationService.isValidEmail(anyString())).thenReturn(true);
//         when(this.validationService.isValidPassword(anyString())).thenReturn(true);
//         when(this.customUserRepository.findByEmail(anyString())).thenReturn(existingUser);

//         ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//             this.authDAO.register(authDTO);
//         });

//         assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
//         assertEquals("Can not register with this email", exception.getReason());
//     }

//     @Test
//     public void testLoginSuccess()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("password123");

//         CustomUser customUser = new CustomUser()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("encodedPassword");
    
//         when(this.authManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
//         when(this.jwtUtil.generateToken(anyString())).thenReturn("jwtToken");
//         when(this.customUserRepository.findByEmail(anyString())).thenReturn(customUser);

//         LoginResponseDTO response = this.authDAO.login(authDTO);

//         assertNotNull(response);
//         assertEquals("user@gmail.com", response.getEmail());
//         assertEquals("jwtToken", response.getToken());
//     }

//     @Test
//     public void testLoginInvalidCredentials()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO()
//             .setFirstName("FirstName")
//             .setLastName("LastName")
//             .setEmail("user@gmail.com")
//             .setPassword("wrongPassword");

//         when(this.authManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
//             .thenThrow(new BadCredentialsException("Invalid credentials"));

//         ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//             this.authDAO.login(authDTO);
//         });

//         assertEquals(HttpStatus.FORBIDDEN, exception.getStatusCode());
//         assertEquals("No valid credentials", exception.getReason());
//     }

//     @Test
//     public void testLoginMissingCredentials()
//     {
//         AuthenticationDTO authDTO = new AuthenticationDTO();

//         ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
//             this.authDAO.login(authDTO);
//         });

//         assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
//         assertEquals("Email is required", exception.getReason());        
//     }
// }
