package hypervisor.vanguard.variable;

import hypervisor.vanguard.sync.VLSyncType;

public interface VLVTypeRunner extends VLVTypeRunnable{

    void start();
    void pause();
    void sync();
    void startAll();
    void pauseAll();
    void syncAll();
    void delay(int delay);
    void delayBy(int amount);
    void syncer(VLSyncType<? extends VLVTypeRunner> syncer);
    void resetDelayTrackers();
    VLSyncType<? extends VLVTypeRunner> syncer();
    void length(Length results);
    int delay();
    boolean paused();
    boolean done();

    void endPointIndex(int index);
    void findEndPointIndex();
    void checkForNewEndPoint(int index);
    VLVTypeRunner endPoint();
    int endPointIndex();

    class Length{

        public float length;
        public int index;

        public Length(){

        }
    }
}
