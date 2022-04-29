package edu.iis.mto.multithread;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ExtendWith(MockitoExtension.class)
class RadarTest {

    @Mock
    private PatriotBattery batteryMock;
    @Mock
    ExecutorService executor;

    @RepeatedTest(100)
    //@Test
    void launchPatriotOnceWhenNoticesAScudMissle() {
        BetterRadar radar = new BetterRadar(10, batteryMock, executor);
        Scud enemyMissle = new Scud();
        radar.notice(enemyMissle);
        verify(executor).execute(any(Runnable.class));
    }

    @RepeatedTest(10)
    void launchPatriotTwiceWhenNoticesTwoMissiles(){
        BetterRadar radar = new BetterRadar(10, batteryMock, executor);
        Scud enemyMissle = new Scud();
        radar.notice(enemyMissle);
        radar.notice(enemyMissle);
        verify(executor, times(2)).execute(any(Runnable.class));
    }
}
