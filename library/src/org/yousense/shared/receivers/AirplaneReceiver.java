package org.yousense.shared.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.yousense.eventlog.EventLog;

public class AirplaneReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getBooleanExtra("state", false))
            EventLog.append("device.airplane.off", null);
        else
            EventLog.append("device.airplane.on", null);
	}

}
