package com.demo.datatypes;

import java.time.LocalDate;

/**
 * Object used while determining if specific rental days are chargeable based on exclusions
 */
public class RentalDays {
    private final LocalDate date;
    private boolean weekday;
    private boolean weekend;
    private boolean holiday;

    /**
     * Must provide the date when constructing this object.
     * Charge data is defaulted as FALSE and should be updated accordingly.
     * @param date Specific date applicable to the given record
     */
    public RentalDays(LocalDate date) {
        this.date = date;
        this.weekday = false;
        this.weekend = false;
        this.holiday = false;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isWeekday() {
        return weekday;
    }

    public void setWeekday(boolean weekday) {
        this.weekday = weekday;
    }

    public boolean isWeekend() {
        return weekend;
    }

    public void setWeekend(boolean weekend) {
        this.weekend = weekend;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }
}