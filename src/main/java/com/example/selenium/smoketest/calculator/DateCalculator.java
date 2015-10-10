package com.example.selenium.smoketest.calculator;

import java.util.Date;

/**
 * Simple date calculator to check if the date is in the past or not.
 *
 * @author @techjedi
 * @author @nevenc
 *
 */
public class DateCalculator {

    private Date now;

    public DateCalculator(Date now) {
        this.now = now;
    }

    public String isDateInThePast(Date date) {
        return (date.before(now)) ? "yes" : "no";
    }

}
