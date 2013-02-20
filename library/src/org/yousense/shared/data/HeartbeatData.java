package org.yousense.shared.data;

public class HeartbeatData {
	boolean exact;
	long interval;
	
	public HeartbeatData(boolean exact, long interval) {
		this.exact = exact;
		this.interval = interval;
	}
}
