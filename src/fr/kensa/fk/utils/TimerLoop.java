package fr.kensa.fk.utils;

import fr.kensa.fk.Main;

import java.util.TimerTask;

public class TimerLoop extends TimerTask {
    Main main;
    public TimerLoop(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        main.getGameManager().getTimemanager().add();
        main.getGameManager().update();
    }
}
