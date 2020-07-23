package fr.kensa.fk.game;

import fr.kensa.fk.GameManager;
import fr.kensa.fk.Main;
import fr.kensa.fk.utils.Field;

public class TimeManager {
    Main main;

    int minute = 0;
    int second = 0;

    public TimeManager(Main main){
        this.main = main;
    }

    public void add(){
        second++;
        if(second == 60){
            second = 0;
            minute ++;
        }
        if(minute == Field.MINUTEPERDAY){
            reset();
            main.getGameManager().addADay();
        }
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public void reset(){
        minute = 0;
        second = 0;
    }
}
