package com.zhizhang.dao.selectOrder;

public class DayOrderInfo {
	private String date;
	private TimeOrderInfo[] timeOrderInfo;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public TimeOrderInfo[] getTimeOrderInfo() {
		return timeOrderInfo;
	}
	public void setTimeOrderInfo(TimeOrderInfo[] timeOrderInfo) {
		this.timeOrderInfo = timeOrderInfo;
	}
}
