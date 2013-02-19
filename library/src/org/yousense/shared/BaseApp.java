package org.yousense.shared;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.google.gson.reflect.TypeToken;
import org.yousense.eventlog.EventLog;
import org.yousense.eventlog.data.Event;
import org.yousense.shared.services.ReceiverService;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventLog.init(getApplicationContext(), latestCachePersistTypes());
        EventLog.append("app.start", null);
        // Start the registration service, which must be alive for some broadcast events.
        // Other services are started elsewhere.
        startService(new Intent(this, ReceiverService.class));
    }

    public static BaseApp get(Context context) {
        return (BaseApp)context.getApplicationContext();
    }

    public Map<String, Type> latestCachePersistTypes() {
        Map<String, Type> types = new HashMap<String, Type>();
        types.put("app.gzip.start", new TypeToken<Event>(){}.getType());
        types.put("app.upload.start", new TypeToken<Event>(){}.getType());
        return types;
    }

    // Event callbacks. Override those if interested in real app.
    public void heartbeat() {}
}
