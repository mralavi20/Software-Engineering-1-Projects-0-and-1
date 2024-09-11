package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Membership> memberships;

    public Player (String name) {
        this.name = name;
        memberships = new ArrayList<Membership>();
    }

    public int add_membership (Membership input_membership) {
        for (int i = 0; i < memberships.size (); i++) {
            if (input_membership.check_overlap (memberships.get (i))) {
                return -1;
            }
        }

        memberships.add (input_membership);

        return 1;
    }

    public String get_name () {
        return name;
    }

    public int get_team_days (String name) {
        return memberships.stream ().filter (element -> element.get_team_name ().equals (name)).mapToInt(Membership::days_count).sum ();
    }
}
