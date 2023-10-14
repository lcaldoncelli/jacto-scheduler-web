package br.com.jacto.authservice.exceptions;

import br.com.jacto.authservice.utils.LanguageUtils;

import java.io.Serial;

public class UserAlreadyRegisteredException extends AuthException {
    @Serial
    private static final long serialVersionUID = 6563590919901946186L;

    public UserAlreadyRegisteredException() {
        super(LanguageUtils.USER_ALREADY_REGISTERED);
    }
}
