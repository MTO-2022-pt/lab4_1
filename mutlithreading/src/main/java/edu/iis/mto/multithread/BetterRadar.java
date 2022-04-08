package edu.iis.mto.multithread;

import java.util.concurrent.Executor;

public class BetterRadar {

    private final int rocketCount;
    private final PatriotBattery battery;
    private final Executor executor;

    public BetterRadar(PatriotBattery battery, int counterRocketsPerMissle, Executor executor) {
        this.battery = battery;
        this.rocketCount = counterRocketsPerMissle;
        this.executor = executor;
    }

    public void notice(Scud enemyMissile) {
        launchPatriot(enemyMissile, rocketCount);
    }

    private void launchPatriot(Scud enemyMissile, int rocketCount) {
        for (int i = 0; i < rocketCount; i++)
            executor.execute(() -> battery.launchPatriot(enemyMissile));
    }
}
