package br.com.jacto.authservice.exceptions;

import br.com.jacto.authservice.utils.LanguageUtils;

import java.io.Serial;

public class LoginFailedException extends AuthException {
    @Serial
    private static final long serialVersionUID = 3759798932753131001L;

    public LoginFailedException() {
        super(LanguageUtils.LOGIN_FAILED);
    }
}
