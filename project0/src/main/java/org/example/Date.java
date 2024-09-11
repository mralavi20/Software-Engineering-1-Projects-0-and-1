
package org.example;

import java.util.Objects;

public class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        if (year < 0 || month < 1 || month > 12 || day < 1 || day > daysOfMonth(month, year))
            throw new IllegalArgumentException("Invalid date");

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date nextDay() {
        int d = day;
        int m = month;
        int y = year;

        d++;
        if (d > daysOfMonth(m, y)) {
            d = 1;
            m++;
            if (m > 12) {
                m = 1;
                y++;
            }
        }
        return new Date(d, m, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date = (Date) o;
        return day == date.day && month == date.month && year == date.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    private static int daysOfMonth(int month, int year) {
        if (month < 1 || month > 12)
            throw new IllegalArgumentException("Invalid value for month");
        if (month < 7)
            return 31;
        else if (month < 12)
            return 30;
        else
            return isLeapYear(year) ? 30 : 29;
    }

    private static boolean isLeapYear(int year) {
        int r = year % 33;
        return r==1 || r==5 || r==9 || r==13 || r==17 || r==22 || r==26 || r==30;
    }

    @Override
    public int compareTo (Date date) {
        if (this.year > date.year) {
            return 1;
        }
        else if (this.year < date.year) {
            return -1;
        }
        else {
            if (this.month > date.month) {
                return 1;
            }
            else if (this.month < date.month) {
                return -1;
            }
            else {
                if (this.day > date.day) {
                    return 1;
                }
                else if (this.day < date.day) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }

    public int diff (Date date) {
        int days = daysOfMonth (date.month, date.year) - date.day + 1;

        for (int i = date.month + 1; i <= 12; i++) {
            days = days +  daysOfMonth (i, date.year);
        }

        for (int i = date.year + 1; i < this.year; i++) {
            days = days + (isLeapYear(i) ? 366 : 365);
        }

        for (int i = 1; i < this.month; i++) {
            days = days + daysOfMonth (i, this.year);
        }

        days = days + this.day;

        return days;
    }
}