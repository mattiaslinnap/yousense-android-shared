package org.yousense.shared.receivers;

import java.util.HashMap;
import java.util.Map;

import org.yousense.common.Pretty;
import org.yousense.eventlog.DebugLog;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.Config;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Receiver for simple, no-extras events.
 * 
 * Screen events cannot be declared in the manifest, have to be registered in code.
 * 
 * External power and battery low/okay events can be derived from ACTION_BATTERY_CHANGED as well.
 * But these events wake up the device no matter how deeply it is sleeping, and can be used to re-start any killed services.
 */
public class SimpleEventReceiver extends BroadcastReceiver {
	
	private static Map<String, String> tags = new HashMap<String, String>();
	
	static {
		tags.put(Intent.ACTION_BATTERY_LOW, "device.battery.low");
		tags.put(Intent.ACTION_BATTERY_OKAY, "device.battery.okay");
		tags.put(Intent.ACTION_POWER_CONNECTED, "device.power.connected");
		tags.put(Intent.ACTION_POWER_DISCONNECTED, "device.power.disconnected");
		tags.put(Intent.ACTION_SCREEN_ON, "device.screen.on");
		tags.put(Intent.ACTION_SCREEN_OFF, "device.screen.off");
		tags.put(Intent.ACTION_USER_PRESENT, "device.screen.unlocked");
	}
	
	public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
		    String tag = tags.get(action);
		    if (tag != null) {
                EventLog.append(tag, null);
                return;
		    }
        }
        DebugLog.eLog(Config.TAG,  "Unexpected intent to SimpleEventReceiver: " + Pretty.prettyPrintIntent(intent));
	}

}
