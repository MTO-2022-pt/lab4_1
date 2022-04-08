package edu.iis.mto.multithread;

public class BetterRadar {

    private int rocket_count = 1;
    private PatriotBattery battery;

    public BetterRadar(PatriotBattery battery) {
        this.battery = battery;
    }

    public BetterRadar(PatriotBattery battery, int rocket_count) {
        this.battery = battery;
        if(rocket_count > 1)
            this.rocket_count = rocket_count;
    }

    public void notice(Scud enemyMissle) {
        launchPatriot(enemyMissle, rocket_count);
    }

    private void launchPatriot(Scud enemyMissle, int rocketCount) {
        Runnable launchPatriotTask = () -> {
            for (int i = 0; i < rocketCount; i++) {
                battery.launchPatriot(enemyMissle);
            }
        };

        Thread launchingThread = new Thread(launchPatriotTask);
        launchingThread.start();
        try{
            launchingThread.join();
        }
        catch (InterruptedException e){
            System.out.println("Launching interrupted");
        }
    }

}
