package fr.kensa.fk.utils;

import fr.kensa.fk.game.Base;
import fr.kensa.fk.game.Team;
import fr.kensa.fk.utils.Field;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Utils {
    
    public static boolean checkInBase(Location loc, Base base){

        Location base1 = base.getLoc2(); //x, plus petites
        Location base2 = base.getLoc1(); //x', plus grandes


        if(loc.getBlockX() >= base1.getBlockX() && loc.getBlockX() <= base2.getBlockX() && loc.getBlockZ() >= base1.getBlockZ() && loc.getBlockZ() <= base2.getBlockZ()){
            System.out.println("in base");
            return true;
        }
        return false;
    }

    public static boolean checkRadius(Player p, Team enmTeam) {
        Location loc = p.getLocation();
        int radius = Field.CHESTRADIUS;

        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();


        // Bukkit.broadcastMessage("team : \n"+main.getTeam(playerTeam).getInEmnCBase().toString());
        //  Bukkit.broadcastMessage("enm : \n"+main.getTeam(enmTeam).getInEmnCBase().toString());


        for (int x = cx - radius; x <= cx + radius; x++) {
            for (int z = cz - radius; z <= cz + radius; z++) {
                for (int y = (cy - radius); y < (cy + radius); y++) {
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + ((cy - y) * (cy - y));

                    if (dist < radius * radius) {
                        Location l = new Location(loc.getWorld(), x, y + 2, z);
                        if (l.getBlock().getType() == Material.CHEST) {
                            if(checkInBase(p.getLocation(),enmTeam.getBase())){
                            return true;
                            }
                        }
                    }
                }
            }
        }
        return false;

    }
  //  x y z     x' y' z'
    
 //  x x'
 //   y y'
 //   z z'

  //  112 73 144 140,73,172
  //  , 28,73,-72, 0,74,-100


}
