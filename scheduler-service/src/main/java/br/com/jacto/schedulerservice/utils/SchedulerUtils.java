package br.com.jacto.schedulerservice.utils;

import java.time.LocalDateTime;

public class SchedulerUtils {
    /**
     * Check if date is valid
     * @param dateTime - LocalDateTime object
     * @return true if DateTime is INVALID
     */
    public static boolean isInvalidDateTime(LocalDateTime dateTime) {
        return dateTime == null;
    }

    /**
     * Check if date is in the past
     * @param dateTime - LocalDateTime object
     * @return true if DateTime is before NOW.
     */
    public static boolean isPastDateTime(LocalDateTime dateTime) {
        return dateTime != null && LocalDateTime.now().isAfter(dateTime);
    }
}
