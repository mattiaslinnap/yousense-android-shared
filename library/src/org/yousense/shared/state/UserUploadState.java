package org.yousense.shared.state;

import org.yousense.eventlog.EventLog;
import org.yousense.eventlog.data.Event;
import org.yousense.shared.data.UserUploadData;

public class UserUploadState {
    public static final String UPLOAD_EVENT_TAG = "user.prefs.upload";

    public static boolean wifiOnly() {
        Event<UserUploadData> event = (Event<UserUploadData>)EventLog.getLatest(UPLOAD_EVENT_TAG);
        if (event == null)
            return false;
        else
            return event.data.wifi_only;
    }

    public static void setWifiOnly(boolean wifiOnly) {
        // Note: make sure not to overwrite other settings when fields are added.
        EventLog.append(UPLOAD_EVENT_TAG, new UserUploadData(wifiOnly));
    }
}
