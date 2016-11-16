package com.zhizhang.dao;

import java.util.ArrayList;
import java.util.List;

public class AllOrderCompanyInfo {
	private List<OrderCompanyInfo> allInfo = new ArrayList<OrderCompanyInfo>();

	public List<OrderCompanyInfo> getAllInfo() {
		return allInfo;
	}

	public void setAllInfo(List<OrderCompanyInfo> allInfo) {
		this.allInfo = allInfo;
	}
	
	
}
