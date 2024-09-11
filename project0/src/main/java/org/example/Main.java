package org.example;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static int find_player (String name, List<Player> players) {
        int i;

        for (i = 0; i < players.size (); i++) {
            if (name.equals(players.get (i).get_name ())) {
                return i;
            }
        }

        return -1;
    }
    
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();

        Date d1 = new Date (18, 11, 1402);

        System.out.println (d1.nextDay ().toString ());

        try {
            FileReader filereader = new FileReader("input.csv");
            CSVReader csvfile = new CSVReader(filereader);
            String[] row;

            while ((row = csvfile.readNext()) != null) {
                int index = find_player (row[0], players);
                if (index == -1) {
                    Player player = new Player(row[0]);
                    player.add_membership(new Membership(row[1],
                            new Date(Integer.parseInt(row[2]), Integer.parseInt(row[3]), Integer.parseInt(row[4])),
                            new Date(Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]))));
                    players.add(player);
                }
                else {
                    players.get(index).add_membership(new Membership(row[1],
                            new Date(Integer.parseInt(row[2]), Integer.parseInt(row[3]), Integer.parseInt(row[4])),
                            new Date(Integer.parseInt(row[5]), Integer.parseInt(row[6]), Integer.parseInt(row[7]))));
                }
            }
        }
        catch (Exception e) {
            System.out.println("File not found\n");
        }

        int index = find_player("Gholam", players);
        System.out.println(players.get(index).get_team_days("Golgohar"));
    }
}