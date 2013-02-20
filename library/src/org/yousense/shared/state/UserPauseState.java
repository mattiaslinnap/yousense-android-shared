package org.yousense.shared.state;

import org.yousense.eventlog.EventLog;
import org.yousense.eventlog.data.Event;
import org.yousense.shared.Config;
import org.yousense.shared.data.UserPauseData;

public class UserPauseState {
    public static final String PAUSE_EVENT_TAG = "user.prefs.pause";

    public static long remainingPauseDelay() {
        Event<UserPauseData> event = (Event<UserPauseData>)EventLog.getLatest(PAUSE_EVENT_TAG);
        if (event == null)
            return 0;
        else if (!event.data.paused || event.timeSince() >= event.data.delay)
            return 0;
        else
            return event.data.delay - event.timeSince();
    }

    public static boolean isPaused() {
        return remainingPauseDelay() > 0;
    }

    public static void setPaused(boolean paused) {
        if (paused)
            EventLog.append(PAUSE_EVENT_TAG, new UserPauseData(true, Config.PAUSE_INTERVAL));
        else
            EventLog.append(PAUSE_EVENT_TAG, new UserPauseData(false, 0));
    }
}
