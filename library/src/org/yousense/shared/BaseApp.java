package org.yousense.shared;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.google.gson.reflect.TypeToken;
import org.yousense.eventlog.EventListener;
import org.yousense.eventlog.EventLog;
import org.yousense.eventlog.data.Event;
import org.yousense.shared.data.UserPauseData;
import org.yousense.shared.data.UserUploadData;
import org.yousense.shared.services.ReceiverService;
import org.yousense.shared.state.UserPauseState;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public abstract class BaseApp extends Application implements EventListener {
    @Override
    public void onCreate() {
        super.onCreate();
        EventLog.init(getApplicationContext(), this, latestCachePersistTypes());
        EventLog.append("app.start", null);
        // Start the registration service, which must be alive for some broadcast events.
        // Other services are started elsewhere.
        startService(new Intent(this, ReceiverService.class));
    }

    public Map<String, Type> latestCachePersistTypes() {
        Map<String, Type> types = new HashMap<String, Type>();
        types.put("app.gzip.start", new TypeToken<Event>(){}.getType());
        types.put("app.upload.start", new TypeToken<Event>(){}.getType());
        types.put("app.upload.success", new TypeToken<Event>(){}.getType());
        types.put("app.upload.fail", new TypeToken<Event>(){}.getType());
        types.put("user.prefs.pause", new TypeToken<Event<UserPauseData>>(){}.getType());
        types.put("user.prefs.upload", new TypeToken<Event<UserUploadData>>(){}.getType());
        return types;
    }
}
