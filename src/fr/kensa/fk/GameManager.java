package fr.kensa.fk;

import fr.kensa.fk.utils.Utils;
import fr.kensa.fk.utils.*;
import fr.kensa.fk.game.GameState;

import fr.kensa.fk.game.TeamName;
import fr.kensa.fk.game.TimeManager;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class GameManager {

    Main main;
    GameState state = GameState.WAITING;
    int day = 1;
    Timer timer = new Timer();
    //Time
    TimeManager timeManager;
    FallenScoreboard fallenScoreboard;


    public GameManager(Main main) {
        this.main = main;
        timeManager = new TimeManager(main);
        fallenScoreboard = new FallenScoreboard(main,this);
        fallenScoreboard.init();


    }

    public void start() {
        if(state != GameState.WAITING)return;
        state = GameState.STARTED;
        timer.schedule(new TimerLoop(main),0,1000);
        System.out.println("------------------------START GAME------------------------------");
        System.out.println("RED");
        for(Player p : main.getTeam(TeamName.RED).getPlayers()){
            System.out.println(p.getName());
            p.teleport(main.teamRed.getBase().getLoc3());
        }
        System.out.println("BLUE");
        for(Player p : main.getTeam(TeamName.BLUE).getPlayers()){
            System.out.println(p.getName());
            p.teleport(main.teamBlue.getBase().getLoc3());

        }

    }

    public void win(TeamName teamName){
        stop();
        for(Player p : Bukkit.getOnlinePlayers()){
            Title.sendTitle(p,ChatColor.GOLD+"LES "+main.getTeam(teamName).getTag()+teamName.name()+ChatColor.GOLD+" ont gagnés",ChatColor.GREEN+"GG",40);
            p.setGameMode(GameMode.SPECTATOR);
            p.playSound(p.getLocation(),Sound.ENTITY_FIREWORK_BLAST,100,100);
        }
    }

    public void update(){
        //System.out.println("update");
        fallenScoreboard.update();
        if(Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),51,94,98)).getType() == Material.DRAGON_EGG){
            //faire spawn le coffre
            Block block = Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),51,94,98));
            block.setType(Material.CHEST);
            Bukkit.getWorld("world").spawnParticle(Particle.CLOUD,new Location(Bukkit.getWorld("world"),51,94,98),2,2,2,0.1,500);
            Chest chest = (Chest) block.getState();
            ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta meta = chestplate.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
            meta.addEnchant(Enchantment.THORNS, 2, true);


            chestplate.setItemMeta(meta);
            Inventory inv = chest.getBlockInventory();
            inv.setItem(0,new ItemStack(Material.COOKED_BEEF,5));
            inv.setItem(1,new ItemStack(Material.COOKED_BEEF,5));
            inv.setItem(2,new ItemStack(Material.GOLD_INGOT,1));
            inv.setItem(3,new ItemStack(Material.DIAMOND,1));
            inv.setItem(4,new ItemStack(Material.DIAMOND_BLOCK,1));
            inv.setItem(5,new ItemStack(Material.DIAMOND,1));
            inv.setItem(6,new ItemStack(Material.GOLD_INGOT,1));
            inv.setItem(7,new ItemStack(Material.COOKED_BEEF,5));
            inv.setItem(8,new ItemStack(Material.COOKED_BEEF,5));
            inv.setItem(9,new ItemStack(Material.IRON_INGOT,5));
            inv.setItem(10,new ItemStack(Material.ARROW,20));
            inv.setItem(11,new ItemStack(Material.IRON_INGOT,5));
            inv.setItem(12,new ItemStack(Material.DIAMOND_BLOCK,1));
            inv.setItem(13,chestplate);
            inv.setItem(14,new ItemStack(Material.DIAMOND_BLOCK,1));
            inv.setItem(15,new ItemStack(Material.IRON_INGOT,5));
            inv.setItem(16,new ItemStack(Material.ARROW,20));
            inv.setItem(17,new ItemStack(Material.IRON_INGOT,5));
            inv.setItem(18,new ItemStack(Material.COOKED_BEEF,5));
            inv.setItem(19,new ItemStack(Material.COOKED_BEEF,5));
            inv.setItem(20,new ItemStack(Material.GOLD_INGOT,1));
            inv.setItem(21,new ItemStack(Material.DIAMOND,1));
            inv.setItem(22,new ItemStack(Material.DIAMOND_BLOCK,1));
            inv.setItem(23,new ItemStack(Material.DIAMOND,1));
            inv.setItem(24,new ItemStack(Material.GOLD_INGOT,1));
            inv.setItem(25,new ItemStack(Material.COOKED_BEEF,5));
            inv.setItem(26,new ItemStack(Material.COOKED_BEEF,5));

            if(day >= Field.NETHERDAY){
                Bukkit.getWorld("world").spawnParticle(Particle.PORTAL,new Location(Bukkit.getWorld("world"),-48,75 ,109),10,5,10,0.1,100);
            }

        }

        checkForWin();

    }

    private void checkForWin() {
        if(getDay() < Field.ASSAUTDAY)return;
        for(TeamName team : main.getTeams()) {
            if (main.getTeam(team).getInEmnCBase().size() == main.getTeam(team).getPlayers().size() && main.getTeam(team).getPlayers().size() != 0) {
                main.getTeam(team).setWinTimer(main.getTeam(team).getWinTimer() + 1);
                   Bukkit.broadcastMessage("+1");
                   Bukkit.broadcastMessage("+1");

            }
            if (main.getTeam(team).getWinTimer() == Field.TIMETOWIN) {
                // Bukkit.broadcastMessage("WIN"+playerTeam.name());
                win(team);
            }
        }
        for(Player p : Bukkit.getOnlinePlayers()) {
            if (main.getPlayerTeam(p) == null) continue;
            TeamName playerTeam = main.getPlayerTeam(p);
            TeamName enmTeam;
            if (playerTeam == TeamName.RED) {
                enmTeam = TeamName.BLUE;
            } else {
                enmTeam = TeamName.RED;
            }
            if (Utils.checkRadius(p, main.getTeam(enmTeam))) {
            if (main.getTeam(main.getPlayerTeam(p)).getInEmnCBase().contains(p)) {
                continue;

                    //yes
                } else {
                    List<Player> newL = main.getTeam(main.getPlayerTeam(p)).getInEmnCBase();
                    newL.add(p);
                    main.getTeam(main.getPlayerTeam(p)).setInEmnCBase(newL);
                }
            }else{

            //no

            if(main.getTeam(main.getPlayerTeam(p)).getInEmnCBase().contains(p)){
                List<Player> newL = main.getTeam(main.getPlayerTeam(p)).getInEmnCBase();
                newL.remove(p);
                main.getTeam(main.getPlayerTeam(p)).setInEmnCBase(newL);


                }

}}
            }




    public void stop(){
        System.out.println("STOPPING");

        state = GameState.FINISHED;
        timer.cancel();
        timeManager.reset();
        day = 1;
        state = GameState.WAITING;


    }



    //getter and setter


    public GameState getState() {
        return state;
    }

    public int getDay() {
        return this.day;
    }

    public String getTimeString() {
        int minute = timeManager.getMinute();
        int second = timeManager.getSecond();
        String minS = "";
        String secS = "";

        if(minute < 10){
            minS = "0"+minute;
        }else{
            minS = minute+"";
        }
        if(second < 10){
            secS = "0"+second;
        }else{
            secS = second+"";
        }

        return minS+":"+secS;
    }

    public List<String> getCheckList() {
        List<String> toReturn = new ArrayList<>();

        if(getDay() >= Field.PVPDAY){
            toReturn.add(ChatColor.GREEN+"✓");
            System.out.println("PvP pass");
        }else{
            toReturn.add(ChatColor.RED+"✖");
            System.out.println("PvP refused");

        }

        if(getDay() >= Field.NETHERDAY){
            toReturn.add(ChatColor.GREEN+"✓");
            System.out.println("Nether pass");

        }else{
            toReturn.add(ChatColor.RED+"✖");
            System.out.println("Nether refused");

        }

        if(getDay() >= Field.ENDERDAY){
            toReturn.add(ChatColor.GREEN+"✓");
            System.out.println("Ender pass");

        }else{
            toReturn.add(ChatColor.RED+"✖");
            System.out.println("Ender refused");

        }

        if(getDay() >= Field.ASSAUTDAY){
            toReturn.add(ChatColor.GREEN+"✓");
            System.out.println("Assaut pass");

        }else{
            toReturn.add(ChatColor.RED+"✖");
            System.out.println("Assaut refused");

        }
        System.out.println("ICI :"+toReturn.toString());

        return toReturn;
    }

    public TimeManager getTimemanager() {
        return timeManager;
    }

    public FallenScoreboard getFallenScoreboard() {
        return fallenScoreboard;
    }

    public Main getMain() {
        return main;
    }

    public void addADay() {
        day = day + Field.ADDEDDAYPERDAY;

        checkForEvent();
    }

    private void checkForEvent() {
        if(day == Field.PVPDAY){
            Bukkit.broadcastMessage(ChatColor.YELLOW+"Le PVP est activé, Vous pouvez désormer taper sur la gueule de vos adversaires");
            return;
        }
        if(day == Field.NETHERDAY){
            Bukkit.broadcastMessage(ChatColor.RED+"Le nether est dès maintenant acctivé.\nLe Portail ce situe a west de l'arbre");
            Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-48,74,109)).setType(Material.AIR);
            Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-47,74,109)).setType(Material.AIR);
            Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-48,73,109)).setType(Material.AIR);
            Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-47,73,109)).setType(Material.AIR);

            Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-145,251,125)).setType(Material.REDSTONE_BLOCK);

            Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),-48 ,60 ,0)).setType(Material.FIRE);






            return;

        }
        if(day == Field.ENDERDAY){
            Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"),54,76,103)).setType(Material.REDSTONE_BLOCK);
            Bukkit.broadcastMessage(ChatColor.BLUE+"Le moment est venu, vous pouvez dès maintenant accéder à l'end,\n-tuer le dragon\n-récupérer son oeuf\n-et le poser dans la salle au coeur de l'arbre pour obtenir une récompense pas piqué des hannetons");
            return;
        }

        if(day == Field.ASSAUTDAY){
            Bukkit.broadcastMessage(ChatColor.RED+"Les assauts sont maintenant activé");
        }
    }
}
