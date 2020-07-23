package fr.kensa.fk.utils;

import fr.kensa.fk.GameManager;
import fr.kensa.fk.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public class FallenScoreboard {

    Main main;
    GameManager gmanager;
    ScoreboardManager manager;
    Scoreboard board;
    Objective objective;

    Score day;
    Score time;
    Score separator1;
    Score pvpindicator;
    Score netherindicator;
    Score enderindicator;
    Score assautindicator;
    Score separator2;



    public FallenScoreboard(Main main,GameManager gameManager){
        this.main = main;
        this.gmanager = gameManager;
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        objective = board.registerNewObjective("Fallen Kingdoms", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        day = objective.getScore("");
        time = objective.getScore("");
        separator1 = objective.getScore("");
        pvpindicator = objective.getScore("");
        netherindicator = objective.getScore("");
        enderindicator = objective.getScore("");
        assautindicator = objective.getScore("");
        separator2 = objective.getScore("");

        objective.setDisplayName("Fallen Kingdoms");
        update();

    }

    public void update(){

        Scoreboard copy = board;
        for(Player p : Bukkit.getOnlinePlayers()){
            p.setScoreboard(copy);
        }
        board.resetScores(day.getEntry());
        board.resetScores(time.getEntry());
        board.resetScores(separator1.getEntry());
        board.resetScores(pvpindicator.getEntry());
        board.resetScores(netherindicator.getEntry());
        board.resetScores(enderindicator.getEntry());
        board.resetScores(assautindicator.getEntry());
        board.resetScores(separator2.getEntry());



        List<String> checkList = getCheckList();


        day = objective.getScore(ChatColor.GOLD + "Jour : "+ChatColor.GREEN+gmanager.getDay());
        time = objective.getScore(ChatColor.GOLD + "> "+ChatColor.GREEN+gmanager.getTimeString());
        separator1 = objective.getScore(ChatColor.GREEN+"-------------");

        pvpindicator = objective.getScore(ChatColor.GOLD + "PVP : "+checkList.get(0));
        netherindicator = objective.getScore(ChatColor.GOLD + "Nether : "+checkList.get(1));
        enderindicator = objective.getScore(ChatColor.GOLD + "Ender : "+checkList.get(2));
        assautindicator = objective.getScore(ChatColor.GOLD + "Assaut : "+checkList.get(3));

        separator2 = objective.getScore(ChatColor.GREEN+"--------------");


        day.setScore(0);
        time.setScore(-1);
        separator1.setScore(-2);
        pvpindicator.setScore(-3);
        netherindicator.setScore(-4);
        enderindicator.setScore(-5);
        assautindicator.setScore(-6);
        separator2.setScore(-7);

        for(Player p : Bukkit.getOnlinePlayers()){
            p.setScoreboard(board);
        }
    }

    public Scoreboard getScoreboard() {
        return board;
    }

    public void init() {
        update();
    }

    public List<String> getCheckList() {
        List<String> toReturn = new ArrayList<>();



        if(gmanager.getDay() >= Field.PVPDAY){
            toReturn.add(ChatColor.GREEN+"✓");
         //   System.out.println("PvP pass");
        }else{
            toReturn.add(ChatColor.RED+"✖");
          //  System.out.println("PvP refused");

        }

        if(gmanager.getDay() >= Field.NETHERDAY){
            toReturn.add(ChatColor.GREEN+"✓");
          //  System.out.println("Nether pass");

        }else{
            toReturn.add(ChatColor.RED+"✖");
          //  System.out.println("Nether refused");

        }

        if(gmanager.getDay() >= Field.ENDERDAY){
            toReturn.add(ChatColor.GREEN+"✓");
        //    System.out.println("Ender pass");

        }else{
            toReturn.add(ChatColor.RED+"✖");
         //   System.out.println("Ender refused");

        }

        if(gmanager.getDay() >= Field.ASSAUTDAY){
            toReturn.add(ChatColor.GREEN+"✓");
        //    System.out.println("Assaut pass");

        }else{
            toReturn.add(ChatColor.RED+"✖");
          //  System.out.println("Assaut refused");

        }
        //System.out.println("ICI :"+toReturn.toString());*/


       /* toReturn.add(ChatColor.GREEN+"✓");
        toReturn.add(ChatColor.GREEN+"✓");
        toReturn.add(ChatColor.GREEN+"✓");
        toReturn.add(ChatColor.GREEN+"✓");*/

        return toReturn;
    }
}
