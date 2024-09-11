package org.example;

public class Membership {
    private String team_name;
    private Date start_date;
    private Date finish_date;

    public Membership (String team_name, Date start_date, Date finish_date) {
        if (start_date.compareTo (finish_date) == 1) {
            throw new IllegalArgumentException("Invalid date");
        }

        this.team_name = team_name;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public boolean check_overlap (Membership other) {
        if (this.finish_date.compareTo (other.start_date) != -1 && this.start_date.compareTo (other.finish_date) != 1) {
            return true;
        }

        return false;
    }

    public int days_count () {
        return finish_date.diff (start_date);
    }

    public String get_team_name () {
        return team_name;
    }
}
