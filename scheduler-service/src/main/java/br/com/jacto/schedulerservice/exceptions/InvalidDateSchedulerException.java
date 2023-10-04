package br.com.jacto.schedulerservice.exceptions;

import br.com.jacto.schedulerservice.utils.LanguageUtils;

import java.io.Serial;

public class InvalidDateSchedulerException extends SchedulerException {
    @Serial
    private static final long serialVersionUID = 4473727261376084183L;

    public InvalidDateSchedulerException() {
        super(LanguageUtils.INVALID_DATE);
    }
}
