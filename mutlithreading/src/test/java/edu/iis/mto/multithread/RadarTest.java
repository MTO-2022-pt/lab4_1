package edu.iis.mto.multithread;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;

    @RepeatedTest(10)
    void launchPatriotOnceWhenNoticesAScudMissle() {
        BetterRadar radar = new BetterRadar(batteryMock, 1);
        Scud enemyMissle = new Scud();
        radar.notice(enemyMissle);
        verify(batteryMock).launchPatriot(enemyMissle);
    }

    @RepeatedTest(20)
    void launchPatriotThreeTimesWhenNoticesAScudMissle() {
        int rocket_count = 3;
        BetterRadar radar = new BetterRadar(batteryMock, rocket_count);
        Scud enemyMissle = new Scud();
        radar.notice(enemyMissle);
        verify(batteryMock, Mockito.times(rocket_count)).launchPatriot(enemyMissle);
    }

    @RepeatedTest(30)
    void launchPatriotThreeTimesWhenNoticesAScudEnemyMissles() {
        int rocket_count = 3; int enemy_rockets = 2;
        BetterRadar radar = new BetterRadar(batteryMock, rocket_count);
        Scud enemyMissle = new Scud();
        for(int i = 0; i < enemy_rockets; i++){
            radar.notice(enemyMissle);
        }
        verify(batteryMock, Mockito.times(rocket_count * enemy_rockets)).launchPatriot(enemyMissle);
    }

    @Test
    void weAreNotAtWorkRightNow() {
        int rocket_count = 3; int enemy_rockets = 0;
        BetterRadar radar = new BetterRadar(batteryMock, rocket_count);
        Scud enemyMissle = new Scud();
        verify(batteryMock, Mockito.times(0)).launchPatriot(enemyMissle);
    }

}
