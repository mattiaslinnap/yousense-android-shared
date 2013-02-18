package org.yousense.shared.data;

public class HeartbeatData {
	String action;
	boolean exact;
	long interval;
	
	public HeartbeatData(String action, boolean exact, long interval) {
		this.action = action;
		this.exact = exact;
		this.interval = interval;
	}
}
