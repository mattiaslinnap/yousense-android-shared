package org.yousense.shared.data;

import android.content.Intent;
import android.os.BatteryManager;

public class BatteryData {
	
	public static final int INVALID = -1;

	public int plugged; // 0 battery, 1 ac, 2 usb
	public int status; // 1 unknown, 2 charging, 3 discharging, 4 not charging, 5 full, 0 should never happen 
	public int level;
	public int max_level;
	
	public BatteryData() {
        plugged = INVALID;
        status = INVALID;
        level = INVALID;
        max_level = INVALID;
	}
	
	public BatteryData(Intent batteryChangedIntent) {
		plugged = batteryChangedIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, INVALID);
		status = batteryChangedIntent.getIntExtra(BatteryManager.EXTRA_STATUS, INVALID);
		level = batteryChangedIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, INVALID);
		max_level = batteryChangedIntent.getIntExtra(BatteryManager.EXTRA_SCALE, INVALID);
	}
	
	public double relativeLevel() {
		if (max_level > 0)
			return (double)level/max_level;
		else
			return 0.0;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (!(o instanceof BatteryData)) return false;
		BatteryData other = (BatteryData)o;
		return plugged == other.plugged && status == other.status && level == other.level && max_level == other.max_level;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
	    result = 31 * result + plugged;
	    result = 31 * result + status;
	    result = 31 * result + level;
	    result = 31 * result + max_level;
	    return result;
	}
	
	
}
