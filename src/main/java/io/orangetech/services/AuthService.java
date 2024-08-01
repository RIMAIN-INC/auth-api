package io.orangetech.services;

import io.orangetech.entity.*;
import io.orangetech.repository.AuthRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.orangetech.services.TokenUtils.generateToken;

@ApplicationScoped
public class AuthService {

    @Inject
    AuthRepository authRepository;

    @Inject
    PasswordEncoder passwordEncoder;

    @ConfigProperty(name="io.orangetech.quarkusjwt.jwt.duration")
    long duration;

    @ConfigProperty(name="mp.jwt.verify.issuer")
    String issuer;

    Logger LOG = LoggerFactory.getLogger(AuthService.class);

    @Transactional
    public User registerUser(UserRequest userRequest){
        Optional<User> user = authRepository.findByEmail(userRequest.getEmail());

        if(user.isPresent()){
            throw new RuntimeException("This account is already created");
        }
        LOG.info("Creating New User Account {}",userRequest.getEmail());
        User newUser = new User();
        newUser.setEmail(userRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        newUser.setUsername(userRequest.getUsername());
        newUser.setRole(Role.USER);
        authRepository.persist(newUser);
        return newUser;
    };

    public AccessToken authenticateUser(LoginRequest loginRequest) throws Exception {
        Optional <User> user = authRepository.findByEmail(loginRequest.getEmail());

        if(user.isEmpty()){
            throw new RuntimeException("No Account Found");
        }
        LOG.info("Authenticating Username and Password {}", loginRequest.getEmail());
        boolean isPasswordMatch = user.get().getPassword().equals(passwordEncoder.encode(loginRequest.getPassword()));
        System.err.println("-------------------authentication-----------");
        if(!isPasswordMatch){
            //String username, Role roles, Long duration, String issuer
            throw new RuntimeException();
        }
        return new AccessToken(generateToken(user.get().getUsername(), user.get().getRole(),duration, issuer));


    };
}
