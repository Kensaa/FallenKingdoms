package fr.kensa.fk.game;

import org.bukkit.Location;

public class Base {
    Location loc1,loc2,loc3 = null;

    public Base(Location loc1,Location loc2,Location loc3){
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.loc3 = loc3;
    }

    public Location getLoc1() {
        return loc1;
    }

    public Location getLoc2() {
        return loc2;
    }

    public Location getLoc3() {
        return loc3;
    }
}
