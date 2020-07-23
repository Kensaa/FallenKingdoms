package fr.kensa.fk.game;

import org.bukkit.Location;

public class Base {
    Location loc1,loc2 = null;

    public Base(Location loc1,Location loc2){
        this.loc1 = loc1;
        this.loc2 = loc2;
    }

    public Location getLoc1() {
        return loc1;
    }

    public Location getLoc2() {
        return loc2;
    }
}
