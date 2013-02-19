package org.yousense.shared.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.yousense.eventlog.EventLog;
import org.yousense.shared.data.NetworkData;

/**
 * IMPORTANT NOTE: disconnection events may never arrive!
 * Always call into getActiveNetworkInfo() to get real latest state if unsure.
 */
public class NetworkReceiver extends BroadcastReceiver {

	public static boolean connected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo active = connectivityManager.getActiveNetworkInfo();
		if (active == null)
			return false;
		else
			return active.isConnected();
	}
	
	public static boolean connectedToWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo active = connectivityManager.getActiveNetworkInfo();
		if (active == null)
			return false;
		else
			return active.isConnected() && active.getType() == ConnectivityManager.TYPE_WIFI;
	}
	
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkData network = new NetworkData(connectivityManager.getActiveNetworkInfo());
        EventLog.append("device.network", network);
	}

}
