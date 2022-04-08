package edu.iis.mto.multithread;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.Executor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BetterRadarTest {
    private static final Scud ENEMY_MISSILE = new Scud();
    @Mock
    private PatriotBattery patriotBatteryMock;
    @Mock
    private Executor executorMock;

    @RepeatedTest(50)
    void checkIfWillItFireOncePerRocket() {
        int rocketsToLaunchPerEnemyMissile = 1;
        BetterRadar betterRadar = new BetterRadar(patriotBatteryMock,rocketsToLaunchPerEnemyMissile,executorMock);
        betterRadar.notice(ENEMY_MISSILE);

        Mockito.verify(executorMock,times(rocketsToLaunchPerEnemyMissile)).execute(any(Runnable.class));
    }

    @RepeatedTest(50)
    void checkIfWillItFireTenTimesPerRocket() {
        int rocketsToLaunchPerEnemyMissile = 10;
        BetterRadar betterRadar = new BetterRadar(patriotBatteryMock,rocketsToLaunchPerEnemyMissile,executorMock);
        betterRadar.notice(ENEMY_MISSILE);

        Mockito.verify(executorMock,times(rocketsToLaunchPerEnemyMissile)).execute(any(Runnable.class));
    }

    @RepeatedTest(50)
    void checkIfWillItFireTenTimesPerRocketAgainstTwoRockets() {
        int rocketsToLaunchPerEnemyMissile = 10;
        BetterRadar betterRadar = new BetterRadar(patriotBatteryMock,rocketsToLaunchPerEnemyMissile,executorMock);
        betterRadar.notice(ENEMY_MISSILE);
        betterRadar.notice(ENEMY_MISSILE);

        Mockito.verify(executorMock,times(2*rocketsToLaunchPerEnemyMissile)).execute(any(Runnable.class));
    }
}