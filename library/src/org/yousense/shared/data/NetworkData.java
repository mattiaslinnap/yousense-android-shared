package org.yousense.shared.data;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkData {
	public static final int INVALID = -1;
	public boolean connected;  // Really connected, not just connecting.
	public boolean wifi;  // Otherwise probably mobile.
	public boolean roaming;  // Making telecoms rich.
	
	public NetworkData(NetworkInfo activeNetwork) {
		if (activeNetwork != null) {
			connected = activeNetwork.isConnected();
			wifi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
			roaming = activeNetwork.isRoaming();
		} else {
			connected = false;
			wifi = false;
			roaming = false;
		}
	}
}
