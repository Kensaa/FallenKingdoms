package fr.kensa.fk;

import fr.kensa.fk.commands.FkCommand;
import fr.kensa.fk.commands.ResetCommand;
import fr.kensa.fk.listeners.FKListener;
import fr.kensa.fk.listeners.GlassPainterListener;
import fr.kensa.fk.game.Team;
import fr.kensa.fk.game.TeamName;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    Team teamBlue = new Team("blue","§1",new Location(Bukkit.getWorld("world"), 140,73 ,172),new Location(Bukkit.getWorld("world"), 112,73,144));
    Team teamRed = new Team("red","§c",new Location(Bukkit.getWorld("world"), 28,73,-72),new Location(Bukkit.getWorld("world"), 0,74,-100));
    GameManager gameManager;

    Map<Player,Location> playersWalkMap = new HashMap<Player,Location>();

    @Override
    public void onEnable() {
        getCommand("fk").setExecutor(new FkCommand(this));
        getCommand("reset").setExecutor(new ResetCommand(this));
        getServer().getPluginManager().registerEvents(new FKListener(this),this);
        getServer().getPluginManager().registerEvents(new GlassPainterListener(this),this);

        gameManager = new GameManager(this);

        ShapelessRecipe slimballCraft = new ShapelessRecipe(new ItemStack(Material.SLIME_BALL,2));
        slimballCraft.addIngredient(Material.DIAMOND);
        slimballCraft.addIngredient(Material.SEEDS);
        slimballCraft.addIngredient(Material.SEEDS);
        slimballCraft.addIngredient(Material.SEEDS);
        slimballCraft.addIngredient(Material.SEEDS);

        getServer().addRecipe(slimballCraft);

    }

    @Override
    public void onDisable() {

    }

    public void addPlayer(Player t, TeamName team) {
        if(team == TeamName.RED){
            System.out.println("Main : added to red the player : "+t.getName());
            teamRed.addPlayer(t);

        }else if(team.equals(TeamName.BLUE)){
            teamBlue.addPlayer(t);


            System.out.println("Main : added to blue the player : "+t.getName());

        }
    }

    public List<Player> getTeamPlayers(TeamName team){
        List<Player> toReturn = null;
        if(team.equals(TeamName.RED)){
            toReturn = teamRed.getPlayers();
        }else if(team.equals(TeamName.BLUE)){
            toReturn = teamBlue.getPlayers();
        }
        return toReturn;
    }

    public Team getTeam(TeamName team){
        Team toReturn = null;
        if(team.equals(TeamName.RED)){
            toReturn = teamRed;
        } else if(team.equals(TeamName.BLUE)){
            toReturn = teamBlue;
        }
        return toReturn;
    }

    public TeamName getPlayerTeam(Player p){
        TeamName toReturn = null;
        if(getTeam(TeamName.RED).getPlayers().contains(p)){
            toReturn = TeamName.RED;
        } else if(getTeam(TeamName.BLUE).getPlayers().contains(p)){
            toReturn = TeamName.BLUE;
        }
        return toReturn;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public List<TeamName> getTeams() {
        List<TeamName> toReturn = new ArrayList<>();
        toReturn.add(TeamName.BLUE);
        toReturn.add(TeamName.RED);
        return toReturn;
    }



    //MINI JEUX

    public void addPlayersWalk(Player p, Location loc) {
        if(playersWalkMap.containsKey(p)){
            playersWalkMap.remove(p);
        }
        playersWalkMap.put(p,loc);
    }
    public Location getLastLocOf(Player p){
        return playersWalkMap.get(p);
    }
}

