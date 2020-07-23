package fr.kensa.fk.listeners;

import fr.kensa.fk.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Map;
import java.util.Random;

public class GlassPainterListener implements Listener {
    Main main;
    public GlassPainterListener(Main plugin) {
        this.main =plugin;
    }

    @EventHandler
    public void onWalkOnGlassEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        World world = p.getWorld();
        World gameWorld = Bukkit.getWorld("world");
        Location loc = p.getLocation();
        loc.setY(loc.getY() - 1.0D);
        Block block = world.getBlockAt(loc);
        Random r = new Random();
        BlockState bs = block.getState();
        Location corner2 = new Location(gameWorld, 53 ,103 ,100);
        Location corner1 = new Location(gameWorld, 49,103 ,96);

        if (bs.getType() == Material.CONCRETE) {
            if (loc.getBlockX() >= corner1.getBlockX() && loc.getBlockX() <= corner2.getBlockX()) {

                if (loc.getBlockZ() <= corner1.getBlockZ() && loc.getBlockZ() >= corner2.getBlockZ()) {

                    Location lastLoc = main.getLastLocOf(p);
                    if(lastLoc == null){
                        main.addPlayersWalk(p,loc);
                        return;
                    }
                    if(lastLoc.getBlockX() == loc.getBlockX() && lastLoc.getBlockZ() == loc.getBlockZ()){return;}

                    int rr = r.nextInt(16);
                    while(rr == 0){
                        rr = r.nextInt(16);
                    }

                    block.setData((byte)rr);

                    main.addPlayersWalk(p,loc);
                }
            }
        }
    }
}