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

	/**
	 * 用供应商的ID查询供应商的信息
	 * @param orderId
	 * @return
	 */
	public OrderCompanyInfo getOrderInfoById(int orderCompId) {
		// TODO Auto-generated method stub
		for(OrderCompanyInfo info : allInfo){
			if(info.getId() == orderCompId){
				return info;
			}
		}
		return null;
	}
	
	
}
