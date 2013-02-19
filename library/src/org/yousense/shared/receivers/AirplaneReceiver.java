package org.yousense.shared.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import org.yousense.common.Pretty;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.Config;

public class AirplaneReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        if (intent.hasExtra("state")) {
            boolean airplane = intent.getBooleanExtra("state", false);
            if (airplane)
                EventLog.append("device.airplane.on", null);
            else
                EventLog.append("device.airplane.off", null);
        }
	}

}
