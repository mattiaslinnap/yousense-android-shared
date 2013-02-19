package org.yousense.shared.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.yousense.common.Pretty;
import org.yousense.eventlog.DebugLog;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.Config;
import org.yousense.shared.data.AlarmData;

/**
 * Receives alarm ringing events from the AlarmClock/DeskClock application.
 * Does not receive events for setting the alarm, or pressing the snooze/dismiss buttons.
 *
 * Snoozing can be identified based on the flags in the raw alarm data.
 * Normal alarms can be set only on 1-minute boundaries, but snooze alarms are set with millisecond accuracy.
 * There are other potential flags that may identify a snooze-repeat alarm going off as well.
 *
 * Add the following to AndroidManifest.xml:
 * <receiver android:name="your-receiver-class">
 *   <intent-filter>
 *     <action android:name="com.android.alarmclock.ALARM_ALERT" />
 *     <action android:name="com.android.deskclock.ALARM_ALERT" />
 *   </intent-filter>
 * </receiver>
 */
public class AlarmClockReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: test this on various Android OS, SDK, manufacturer extensions, etc., and write other parsing methods.
        try {
            AlarmData alarm = AlarmData.parseAlarmFromBinaryIntentData(intent);
            EventLog.append("device.alarmclock", alarm);
        } catch (Exception e) {
            EventLog.append("device.alarmclock", null);
            DebugLog.eLog(Config.TAG,  "Error parsing alarm clock data for intent: " + Pretty.prettyPrintIntent(intent), e);
        }
    }
}
