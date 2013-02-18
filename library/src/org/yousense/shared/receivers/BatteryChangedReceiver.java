package org.yousense.shared.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.data.BatteryData;


public class BatteryChangedReceiver extends BroadcastReceiver {
	
	private static BatteryData lastBattery = null;
	
	public void onReceive(Context context, Intent intent) {
		BatteryData battery = new BatteryData(intent);
		if (!battery.equals(lastBattery)) {
            lastBattery = battery;
            EventLog.append("device.battery.level", battery);
		}
	}

}
