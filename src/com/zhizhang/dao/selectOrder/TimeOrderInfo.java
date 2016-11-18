package com.zhizhang.dao.selectOrder;

public class TimeOrderInfo {
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
