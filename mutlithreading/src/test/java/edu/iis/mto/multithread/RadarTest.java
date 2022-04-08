package edu.iis.mto.multithread;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;

    @Mock
    private ExecutorService executorService;

    @RepeatedTest(100)
    void launchPatriotOnceWhenNoticesAScudMissile() {
        int rocketsCount = 50;
        BetterRadar radar = new BetterRadar(batteryMock, rocketsCount, executorService);
        Scud enemyMissile = new Scud();
        radar.notice(enemyMissile);
        verify(executorService).execute(any(Runnable.class));
    }

}
