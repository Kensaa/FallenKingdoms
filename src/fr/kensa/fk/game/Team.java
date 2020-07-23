package fr.kensa.fk.game;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    String name;
    String tag;
    List<Player> players = new ArrayList<>();
    Base base;
    List<Player> inEmnCBase = new ArrayList<>();
    int winTimer = 0;

    public Team(String name, String tag, Location loc1, Location loc2){
        this.name = name;
        this.tag = tag;
        this.base = new Base(loc1,loc2);
    }

    public void addPlayer(Player p){
        if(!players.contains(p)){
            players.add(p);
            p.setCustomName(tag + p.getName() + ChatColor.RESET);
            p.setCustomNameVisible(true);
            p.setPlayerListName(tag + p.getName() + ChatColor.RESET);
            p.setDisplayName(tag + p.getName() + ChatColor.RESET);



        }
    }

    public void removePlayer(Player p){
        if(players.contains(p)){
            players.remove(p);
        }
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getTag() {
        return tag;
    }

    public Base getBase() {
        return base;
    }

    public void setWinTimer(int winTimer) {
        this.winTimer = winTimer;
    }

    public int getWinTimer() {
        return winTimer;
    }

    public List<Player> getInEmnCBase() {
        return inEmnCBase;
    }

    public void setInEmnCBase(List<Player> inEmnCBase) {
        this.inEmnCBase = inEmnCBase;
    }
}
