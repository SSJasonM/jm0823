package com.demo;

import com.demo.datatypes.RentalDays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class containing the functionality for identifying possible exclusionary days
 * defined by the specific tool type being rented.
 */
public class ExclusionManager {
    /**
     * Compiles a collection that identifies each day in the given range as either a weekday or weekend,
     * and also denotes whether that date is considered a holiday.
     * @param date The starting chargeable date of the rental period
     * @param days The total amount of days in the rental period
     * @return A collection with an entry for each rental day that denotes it as weekday/weekend, and if it's a holiday
     */
    public Collection<RentalDays> getRentalDays(LocalDate date, int days) {
        Collection<RentalDays> rentalDays = new ArrayList<>();

        for (int i = 0; i < days; i++) {
            RentalDays currentDay = new RentalDays(date);

            if (this.isWeekendDay(date)) {
                currentDay.setWeekend(true);
            } else {
                currentDay.setWeekday(true);
            }

            if (this.isHoliday(date)) {
                currentDay.setHoliday(true);
            }

            rentalDays.add(currentDay);
            date = date.plusDays(1);
        }

        return rentalDays;
    }

    /**
     * Given a specific date, determine if the date falls on a Saturday or Sunday
     * @param date Specific date to check
     * @return Boolean value that returns TRUE if date is a weekend, FALSE if date is a weekday
     */
    private boolean isWeekendDay(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * Method that determines if a given date matches when the Fourth of July is celebrated.
     * If July 4th falls on a Saturday, it is celebrated on Friday, July 3rd.
     * If July 4th falls on a Sunday, it is celebrated on Monday, July 5th.
     * @param date Specific date to check
     * @return Boolean value that returns TRUE if date is celebrated as the Fourth of July, FALSE if not
     */
    private boolean isJulyFourth(LocalDate date) {
        return date.getMonthValue() == 7 &&
                ((date.getDayOfMonth() == 3 && date.getDayOfWeek() == DayOfWeek.FRIDAY) ||
                        (date.getDayOfMonth() == 5 && date.getDayOfWeek() == DayOfWeek.MONDAY) ||
                        (date.getDayOfMonth() == 4 && date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY));
    }

    /**
     * Method that determines if a given date is Labor Day.
     * Labor Day is calculated as the first Monday in September.
     * @param date Specific date to check
     * @return Boolean value that returns TRUE if date is Labor Day, FALSE if not
     */
    private boolean isLaborDay(LocalDate date) {
        return date.getMonthValue() == 9 && date.getDayOfMonth() <= 7 && date.getDayOfWeek() == DayOfWeek.MONDAY;
    }

    /**
     * Wrapper function to determine if the given date is a Holiday
     * @param date Specific date to check
     * @return Boolean value that returns TRUE if date is either Labor Day or the Fourth of July, FALSE if not
     */
    private boolean isHoliday(LocalDate date) {
        return this.isJulyFourth(date) || this.isLaborDay(date);
    }
}