package br.com.jacto.schedulerservice.exceptions;

import br.com.jacto.schedulerservice.utils.LanguageUtils;

import java.io.Serial;

public class ScheduleNotFoundException extends SchedulerException {
    @Serial
    private static final long serialVersionUID = -2086589740268921567L;

    public ScheduleNotFoundException() {
        super(LanguageUtils.SCHEDULE_NOT_FOUND);
    }
}
