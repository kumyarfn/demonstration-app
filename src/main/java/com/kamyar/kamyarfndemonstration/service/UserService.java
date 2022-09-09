package com.kamyar.kamyarfndemonstration.service;

import com.kamyar.kamyarfndemonstration.dto.request.user.UserRegistrationDto;
import com.kamyar.kamyarfndemonstration.db.entity.UserEntity;
import com.kamyar.kamyarfndemonstration.enums.Role;
import com.kamyar.kamyarfndemonstration.exception.UserException;
import com.kamyar.kamyarfndemonstration.dto.response.HttpResponse;
import com.kamyar.kamyarfndemonstration.db.repository.UserRepository;
import com.kamyar.kamyarfndemonstration.security.principal.UserPrincipal;
import com.kamyar.kamyarfndemonstration.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.kamyar.kamyarfndemonstration.enums.ResultMessage.*;


@Service
@Qualifier("userDetailsService")
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserPrincipal.convertFromUserEntity(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User by the name of: " + username + " was not found.")));
    }

    public HttpResponse registerUser(UserRegistrationDto dto, Role role) {
        register(dto, role);
        return HttpResponse.create(SUCCESS_RESULT.getCode(), USER_REGISTERED_SUCCESSFULLY.getMessage());
    }

    /**
     * Checks if the username or phoneNumber has not been
     * in the db before then persists the user.
     */
    public UserEntity register(UserRegistrationDto dto, Role role) {
        validateNewUser(dto.getUsername(), dto.getPhoneNumber());
        return userRepository.save(getUserForRegistering(dto, role));
    }

    /**
     * This is for authenticating the user via
     * authentication manager bean and returns the desired
     * JWT in the response's header.
     */
    public ResponseEntity<HttpResponse> authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserEntity user = userRepository.findByUsername(username).get();
        HttpHeaders jwtHeader = jwtTokenProvider.getJwtHeader(UserPrincipal.convertFromUserEntity(user));
        return new ResponseEntity<>(HttpResponse.create(SUCCESS_RESULT.getCode(), USER_LOGIN_WAS_SUCCESSFUL.getMessage()), jwtHeader, HttpStatus.OK);
    }


    /**
     * Checks if the username or phoneNumber has not been
     * in the db before
     */
    public void validateNewUser(String username, String phoneNumber) {
        userRepository.findByUsername(username).ifPresent(x -> {
            throw new UserException(USERNAME_ALREADY_EXISTS);
        });
        userRepository.findByPhoneNumber(phoneNumber).ifPresent(x -> {
            throw new UserException(PHONE_NUMBER_ALREADY_EXISTS);
        });
    }

    /**
     * Converts a user into a user entity and it also
     * gets Role as input.
     */
    private UserEntity getUserForRegistering(UserRegistrationDto dto, Role role) {
        UserEntity user = new UserEntity();
        user.setId(null);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setJoinDate(LocalDate.now());
        user.setIsActive(Boolean.TRUE);
        user.setIsNonLocked(Boolean.TRUE);
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);
        return user;
    }

    /**
     * Fetches the desired user specified by
     * it id.
     */
    public UserEntity getUserById(String userID){
        return userRepository.findById(userID).orElseThrow(() -> new UserException(USER_WAS_NOT_FOUND));
    }

}
