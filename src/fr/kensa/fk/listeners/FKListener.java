package fr.kensa.fk.listeners;

import fr.kensa.fk.Main;
import fr.kensa.fk.utils.Field;
import fr.kensa.fk.game.GameState;
import fr.kensa.fk.game.TeamName;
import fr.kensa.fk.utils.Utils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class FKListener implements Listener {
    Main main;
    public FKListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        System.out.println("called");
        Scoreboard board = main.getGameManager().getFallenScoreboard().getScoreboard();
        event.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(30.0d);
        event.getPlayer().setScoreboard(board);
        if(main.getPlayerTeam(event.getPlayer()) != null){

        }
       // System.out.println(main.getGameManager());

    }

    @EventHandler
    public void onPlayerDamageOtherPlayer(EntityDamageByEntityEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player damaged = (Player) event.getEntity();
        if(!(event.getDamager() instanceof Player)) return;
        Player damager = (Player) event.getDamager();

        if(main.getPlayerTeam(damaged).equals(main.getPlayerTeam(damager))){
            event.setCancelled(true);
            return;
        }else if(main.getGameManager().getDay() < Field.PVPDAY){
            event.setCancelled(true);
            return;
        }else if(main.getGameManager().getState() != GameState.STARTED){
            event.setCancelled(true);
            return;
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent event){
        if(event.getPlayer().isOp()){
         //   System.out.println("IS OP");
            return;
        }

        if(main.getGameManager().getState() == GameState.WAITING){
            //System.out.println("IS WAITING");
            event.setCancelled(true);
            return;
        }
        if(event.getBlock().getType() == Material.TORCH || event.getBlock().getType() == Material.SIGN){
            return;
        }

        if(Utils.checkInBase(event.getBlock().getLocation(),Field.netherBase) || Utils.checkInBase(event.getBlock().getLocation(),Field.netherBase) || Utils.checkInBase(event.getBlock().getLocation(),Field.spawnBase)){
            event.setCancelled(true);
            return;
        }

        if(event.getBlock().getType() == Material.TNT){
          //  System.out.println("TNT");

            if(main.getGameManager().getDay() >= Field.ASSAUTDAY){
              //  System.out.println("TNT+ASSAUT");

                return;
            }
        }
        if(main.getPlayerTeam(event.getPlayer()) == null){
            //System.out.println("DONT HAVE TEAM");

            return;
        }
        if(!Utils.checkInBase(event.getBlock().getLocation(),main.getTeam(main.getPlayerTeam(event.getPlayer())).getBase())){
            //System.out.println("NOT IN BASE");

            if(event.getBlock().getType() == Material.TORCH || event.getBlock().getType() == Material.SIGN){
                //System.out.println("IS A TORCH OR A SIGN");

                return;
            }
            event.setCancelled(true);
            return;
        }

    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent event){
        if(event.getPlayer().isOp())return;
        if(main.getGameManager().getState() == GameState.WAITING){
            event.getPlayer().sendMessage("§cVOUS NE POUVEZ PAS PLACER DE BLOCK ICI");
            event.setCancelled(true);
            return;
        }

        if(Utils.checkInBase(event.getBlock().getLocation(),Field.netherBase) || Utils.checkInBase(event.getBlock().getLocation(),Field.netherBase) || Utils.checkInBase(event.getBlock().getLocation(),Field.spawnBase)){
            event.setCancelled(true);
            return;
        }

        TeamName playerTeam = main.getPlayerTeam(event.getPlayer());
        TeamName enmTeam;
        if(playerTeam == TeamName.RED){
            enmTeam = TeamName.BLUE;
        }else{
            enmTeam = TeamName.RED;
        }
        if(Utils.checkInBase(event.getBlock().getLocation(),main.getTeam(enmTeam).getBase())){
            event.getPlayer().sendMessage("§cVOUS NE POUVEZ PAS PLACER DE BLOCK ICI");
            event.setCancelled(true);
            return;
        }
    }

}
