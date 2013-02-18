package org.yousense.shared.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.yousense.eventlog.EventLog;
import org.yousense.shared.data.TimeZoneData;

/**
 * Listens for TIME_CHANGED (actual intent action named TIME_SET) and TIMEZONE_CHANGED broadcasts.
 * These might help detect user messing with the wall clock or timezones, introducing problems into data ordering.
 * 
 * These should not be relied on to actually detect or clean up bad timestamps - phone might even reboot to 1980.
 */
public class TimeChangedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_TIME_CHANGED.equals(intent.getAction())) {
            EventLog.append("device.time_changed", null);  // System.currentTimeMillis() is included in every event.
		} else if (Intent.ACTION_TIMEZONE_CHANGED.equals(intent.getAction())) {
            EventLog.append("device.timezone_changed", new TimeZoneData(intent.getStringExtra("time-zone")));
		}		
	}

}
