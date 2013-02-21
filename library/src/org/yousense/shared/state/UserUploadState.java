package org.yousense.shared.state;

import org.yousense.eventlog.EventLog;
import org.yousense.eventlog.data.Event;
import org.yousense.shared.data.UserUploadData;

public class UserUploadState {
    public static final String UPLOAD_EVENT_TAG = "user.prefs.upload";

    public static boolean filesOnMobile() {
        Event<UserUploadData> event = (Event<UserUploadData>)EventLog.getLatest(UPLOAD_EVENT_TAG);
        if (event == null)
            return true;
        else
            return event.data.files_on_mobile;
    }

    public static void setFilesOnMobile(boolean filesOnMobile) {
        // Note: make sure not to overwrite other settings when fields are added.
        EventLog.append(UPLOAD_EVENT_TAG, new UserUploadData(filesOnMobile));
    }
}
