package fr.kensa.fk.utils;

import fr.kensa.fk.game.Base;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Field {
    public static final int PVPDAY = 3;
    public static final int NETHERDAY = 5;
    public static final int ENDERDAY = 7;
    public static final int ASSAUTDAY = 8;
    public static final int MINUTEPERDAY = 1;
    public static final int CHESTRADIUS = 3;
    public static final int TIMETOWIN = 60;
    public static final int ADDEDDAYPERDAY = 1;
    public static final Base spawnBase = new Base(new Location(Bukkit.getWorld("world"),62 ,117 ,111),new Location(Bukkit.getWorld("world"),27,0,78));
    public static final Base netherBase = new Base(new Location(Bukkit.getWorld("world"),-59,57,79),new Location(Bukkit.getWorld("world"),-32,83,119));
}
