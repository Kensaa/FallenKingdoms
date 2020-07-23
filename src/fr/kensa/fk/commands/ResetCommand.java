package fr.kensa.fk.commands;

import fr.kensa.fk.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand implements CommandExecutor {
    Main main;
    public ResetCommand(Main main) {
    this.main =main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player))return false;


        Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-48,74,109)).setType(Material.STONE);
        Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-47,74,109)).setType(Material.STONE);
        Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-48,73,109)).setType(Material.STONE);
        Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-47,73,109)).setType(Material.STONE);
        Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-48 ,60 ,0)).setType(Material.AIR);

        Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-145,251,125)).setType(Material.AIR);

        Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),54,76,103)).setType(Material.AIR);

        return false;
    }
}
