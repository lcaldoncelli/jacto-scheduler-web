package br.com.jacto.authservice.service;

import br.com.jacto.authservice.entity.UserEntity;
import br.com.jacto.authservice.exceptions.AuthException;
import br.com.jacto.authservice.exceptions.LoginFailedException;
import br.com.jacto.authservice.exceptions.UserAlreadyRegisteredException;
import br.com.jacto.authservice.model.CreateUserModel;
import br.com.jacto.authservice.model.LoginModel;
import br.com.jacto.authservice.model.TokenResponseModel;
import br.com.jacto.authservice.model.UserModel;
import br.com.jacto.authservice.repository.UserRepository;
import br.com.jacto.authservice.security.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;

    public TokenResponseModel create(CreateUserModel model) throws AuthException {
        Optional<UserEntity> existingEntity = userRepository.findByEmail(model.getEmail());
        if (existingEntity.isEmpty()) {
            UserEntity entity = CreateUserModel.toEntity(model);
            entity.setPassword(encodePassword(model.getPassword()));
            userRepository.save(entity);
            return login(CreateUserModel.toLoginModel(model));
        }
        throw new UserAlreadyRegisteredException();
    }

    public TokenResponseModel login(LoginModel model) throws AuthException {
        try {
            Optional<UserEntity> entity = userRepository.findByEmail(model.getEmail());
            if (entity.isPresent()) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);
                return TokenResponseModel.toModel(entity.get(), jwt);
            }
        } catch (Exception e) {
            log.info("Authentication failed", e);
        }
        throw new LoginFailedException();
    }

    public UserModel getUser(long userId) throws AuthException {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return UserModel.toModel(optionalUser.get());
        }
        throw new LoginFailedException();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
