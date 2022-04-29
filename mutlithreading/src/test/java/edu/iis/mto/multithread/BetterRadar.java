package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;

public class BetterRadar {
    private int rockets;
    private PatriotBattery battery;
    private ExecutorService executor;

    public BetterRadar(int rockets, PatriotBattery battery, ExecutorService executor) {
        this.rockets = rockets;
        this.battery = battery;
        this.executor = executor;
    }

    public void notice(Scud missile){
        Runnable salve = () -> {
            for(int i = 0; i < rockets; i++){
                battery.launchPatriot(missile);
            }};
        executor.execute(salve);
    }


}
