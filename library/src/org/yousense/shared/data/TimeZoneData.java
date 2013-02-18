package org.yousense.shared.data;

/**
 * id is expected to be java.common.TimeZone.getID().
 */
public class TimeZoneData {
	String timezone_id;
	
	public TimeZoneData(String timezone_id) {
		this.timezone_id = timezone_id;
	}
}
