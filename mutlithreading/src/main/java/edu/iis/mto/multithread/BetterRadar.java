package edu.iis.mto.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BetterRadar
{
    private final int rocketsCount;
    private final PatriotBattery battery;
    private final ExecutorService executorService;

    public BetterRadar(PatriotBattery battery, int rocketsCount) {
        this.battery = battery;
        this.rocketsCount = rocketsCount;
        executorService = Executors.newSingleThreadExecutor();
    }

    public BetterRadar(PatriotBattery battery, int rocketsCount, ExecutorService executorService) {
        this.battery = battery;
        this.rocketsCount = rocketsCount;
        this.executorService = executorService;
    }

    public void notice(Scud enemyMissile) {
        this.executorService.execute(() -> {
            for (int i = 0; i < rocketsCount; i++) {
                this.battery.launchPatriot(enemyMissile);
            }
        });
    }
}
