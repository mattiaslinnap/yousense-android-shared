package org.yousense.shared.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import org.yousense.eventlog.DebugLog;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.BaseApp;
import org.yousense.shared.Config;
import org.yousense.shared.data.HeartbeatData;

/**
 * Regular wakeups.
 */
public class HeartbeatReceiver extends BroadcastReceiver {
    public static final String TAG = Config.TAG;
    public static final String ACTION_HEARTBEAT = "org.yousense.intent.action.HEARTBEAT";
	
	public static void startRepeating(Context context) {
		AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		
		// Regular heartbeats to detect death, every 5 minutes.
		alarmManager.setRepeating(
				AlarmManager.ELAPSED_REALTIME_WAKEUP,
				SystemClock.elapsedRealtime(),
                Config.HEARTBEAT_INTERVAL,
				makePendingIntent(context));
        DebugLog.dLog(TAG, "Heartbeat timer started");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
        // Set data parameters based on current code.
        EventLog.append("app.heartbeat", new HeartbeatData(true, Config.HEARTBEAT_INTERVAL));
	}

	private static PendingIntent makePendingIntent(Context context) {
		Intent intent = new Intent(context, HeartbeatReceiver.class);
		intent.setAction(ACTION_HEARTBEAT);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		return pendingIntent;
	}
}
