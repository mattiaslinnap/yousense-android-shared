package org.yousense.shared;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.services.ReceiverService;


public class BaseApp extends Application {
    public static BaseApp get(Context context) {
        return (BaseApp)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventLog.init(getApplicationContext());
        EventLog.append("app.start", null);
        // Start the registration service, which must be alive for some broadcast events.
        // Other services are started elsewhere.
        startService(new Intent(this, ReceiverService.class));
    }

    // Event callbacks. Override those if interested in real app.
    public void heartbeat() {}
}
