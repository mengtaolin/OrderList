package com.zhizhang.dao.selectOrder;

public class TimeOrderInfo {
	/**
	 * 点餐时间  早餐，午餐，晚餐
	 */
	private String time;
	private SelectOrderInfo[] selectOrderInfo;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public SelectOrderInfo[] getSelectOrderInfo() {
		return selectOrderInfo;
	}
	public void setSelectOrderInfo(SelectOrderInfo[] selectOrderInfo) {
		this.selectOrderInfo = selectOrderInfo;
	}

}
