package fr.kensa.fk.commands;

import fr.kensa.fk.Main;
import fr.kensa.fk.game.Team;
import fr.kensa.fk.game.TeamName;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FkCommand implements CommandExecutor {

    Main main;
    public FkCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(!(commandSender instanceof Player)) return false;
        Player p = (Player)commandSender;

        switch (args[0]){
            case "team":
            System.out.println("sub 1 : team");
                switch (args[1]){

                    case "add":
                        System.out.println("sub 2 : add");

                        Player t = Bukkit.getPlayer(args[2]);
                        if(args[3].toLowerCase().equals("red")){
                            System.out.println("red");

                            main.addPlayer(t, TeamName.RED);


                        } else if(args[3].toLowerCase().equals("blue")){
                            System.out.println("blue");

                            main.addPlayer(t, TeamName.BLUE);


                        }

                        break;

                }
                break;

            case "game":
                System.out.println("sub 1 : game");
                switch (args[1]){

                    case "start":
                        System.out.println("sub 2 : start");
                        main.getGameManager().start();
                        break;

                    case "stop":
                        System.out.println("sub 2 : stop");
                        main.getGameManager().stop();
                        break;
                }
                break;

            case "tp":
                Player t = Bukkit.getPlayer(args[1]);
                Team team = main.getTeam(TeamName.RED);
                if(args[2].equalsIgnoreCase("red")){

                    team = main.getTeam(TeamName.RED);
                }
                if(args[2].equalsIgnoreCase("blue")){
                    team = main.getTeam(TeamName.BLUE);
                }
                if(args[3].equalsIgnoreCase("1")){
                    t.teleport(team.getBase().getLoc1());
                }
                if(args[3].equalsIgnoreCase("2")){
                    t.teleport(team.getBase().getLoc2());
                }
                if(args[3].equalsIgnoreCase("3")){
                    t.teleport(team.getBase().getLoc3());
                }
                break;
        }

        return true;
    }
}
// /fk team add <pseudo> <team>