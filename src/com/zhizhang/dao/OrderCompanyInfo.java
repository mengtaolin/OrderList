package com.zhizhang.dao;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class OrderCompanyInfo {
	private int id;
	private String name;
	private String telNum;
	private String qqNum;
	private String address;
	private String catchword;
	private int type;
	
	private List<OrderListInfo> listInfo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getQqNum() {
		return qqNum;
	}
	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCatchword() {
		return catchword;
	}
	public void setCatchword(String catchword) {
		this.catchword = catchword;
	}
	public List<OrderListInfo> getListInfo() {
		return listInfo;
	}
	public void setListInfo(List<OrderListInfo> listInfo) {
		this.listInfo = listInfo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void parsData(Element element) {
		this.id = Integer.parseInt(element.attributeValue("id"));
		this.name = element.attributeValue("name");
		this.telNum = element.attributeValue("telNum");
		this.qqNum = element.attributeValue("qqNum");
		this.address = element.attributeValue("address");
		this.catchword = element.attributeValue("catchword");
		this.type = Integer.parseInt(element.attributeValue("type"));
		
		listInfo = new ArrayList<OrderListInfo>();
		@SuppressWarnings("unchecked")
		List<Element> elementChildren = element.elements();
		for(Element child : elementChildren){
			OrderListInfo orderListInfo = new OrderListInfo();
			orderListInfo.parseData(child);
			listInfo.add(orderListInfo);
		}
	}
	
	
}
