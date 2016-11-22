package com.zhizhang.dao.selectOrder;

public class DayOrderInfo {
	/**
	 * 点餐日期
	 */
	private String date;
	/**
	 * 点餐时间
	 */
	private String time;
	private TimeOrderInfo[] timeOrderInfo;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public TimeOrderInfo[] getTimeOrderInfo() {
		return timeOrderInfo;
	}
	public void setTimeOrderInfo(TimeOrderInfo[] timeOrderInfo) {
		this.timeOrderInfo = timeOrderInfo;
	}
}
