package br.com.jacto.authservice.service;

import br.com.jacto.authservice.entity.UserEntity;
import br.com.jacto.authservice.exceptions.AuthException;
import br.com.jacto.authservice.exceptions.LoginFailedException;
import br.com.jacto.authservice.model.CreateUserModel;
import br.com.jacto.authservice.model.LoginModel;
import br.com.jacto.authservice.model.TokenResponseModel;
import br.com.jacto.authservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    public TokenResponseModel create(CreateUserModel model) throws AuthException {
        UserEntity entity = CreateUserModel.toEntity(model);
        entity.setPassword(encodePassword(model.getPassword()));
        userRepository.save(entity);
        return login(CreateUserModel.toLoginModel(model));
    }

    public TokenResponseModel login(LoginModel model) throws AuthException {
        Optional<UserEntity> entity = userRepository.findByEmail(model.getEmail());
        if (entity.isPresent()) {
            if (checkPassword(model.getPassword(), entity.get().getPassword())) {
                return TokenResponseModel.toModel(entity.get(), "TOKEN");
            }
        }
        throw new LoginFailedException();
    }

    public TokenResponseModel refreshToken() throws AuthException {
        //TODO
        return null;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

}
