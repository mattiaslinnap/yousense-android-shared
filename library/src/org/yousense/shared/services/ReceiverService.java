package org.yousense.shared.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.data.TelephonyData;
import org.yousense.shared.receivers.BatteryChangedReceiver;
import org.yousense.shared.receivers.SimpleEventReceiver;
import org.yousense.shared.receivers.HeartbeatReceiver;

/**
 * ReceiverService is started from App.onCreate().
 * It should stay running at all times, and keep registrations to BroadcastReceivers that cannot be put into AndroidManifest.xml.
 */
public class ReceiverService extends Service {

    BatteryChangedReceiver batteryChangedReceiver;
    SimpleEventReceiver simpleEventReceiver;

    public IBinder onBind(Intent intent) {
        // No binding.
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        // Android docs: "if this service's process is killed while it is started,
        // then it will be scheduled for a restart and the last delivered Intent re-delivered to it again."
        // Note: it won't be killed while onCreate() or onStartCommand() are executing.
        return START_REDELIVER_INTENT;
    }

    public void onCreate() {
        super.onCreate();
        EventLog.append("device.telephony", new TelephonyData(this));

        // Cannot register these via AndroidManifest.xml. Service keeps a handle until it is stopped/killed.
        EventLog.append("app.register_receivers", null);
        batteryChangedReceiver = new BatteryChangedReceiver();
        simpleEventReceiver = new SimpleEventReceiver();
        registerReceiver(batteryChangedReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(simpleEventReceiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(simpleEventReceiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
        registerReceiver(simpleEventReceiver, new IntentFilter(Intent.ACTION_USER_PRESENT));

        /*
        prefsChangedListener = new PrefsChangedListener(this);
        SharedPrefs.prefs(this).registerOnSharedPreferenceChangeListener(prefsChangedListener);
        */
        HeartbeatReceiver.startRepeating(this);
    }

}
