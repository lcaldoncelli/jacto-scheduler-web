package br.com.jacto.schedulerservice.exceptions;


import java.io.Serial;

public class SchedulerException extends Exception {
    @Serial
    private static final long serialVersionUID = 1000370839289383166L;
    public SchedulerException(String msg, Throwable t) {
        super(msg, t);
    }

    public SchedulerException(String msg) {
        super(msg);
    }
}
