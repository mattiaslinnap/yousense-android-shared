package org.yousense.shared.data;

public class UserPauseData {
    public boolean paused;
    public long delay;

    public UserPauseData(boolean paused, long delay) {
        this.paused = paused;
        this.delay = delay;
    }
}
