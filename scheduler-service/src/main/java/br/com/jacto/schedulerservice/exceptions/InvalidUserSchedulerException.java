package br.com.jacto.schedulerservice.exceptions;

import br.com.jacto.schedulerservice.utils.LanguageUtils;

import java.io.Serial;

public class InvalidUserSchedulerException extends SchedulerException {
    @Serial
    private static final long serialVersionUID = 3759778932753131001L;

    public InvalidUserSchedulerException() {
        super(LanguageUtils.INVALID_USER);
    }
}
