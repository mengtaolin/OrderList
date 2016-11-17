package com.zhizhang.dao;

import org.dom4j.Element;

import com.zhizhang.interfaces.XmlInterface;

public class OrderPriceInfo implements XmlInterface{
	private int compId;
	private String compName;
	private int orderId;
	private String orderName;
	private float price;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String toXml() {
		// TODO Auto-generated method stub
		String xml = "<orderPriceInfo>";
		xml += "<compId>" + this.getCompId() + "</compId>";
		xml += "<compName>" + this.getCompName() + "</compName>";
		xml += "<orderId>" + this.getOrderId() + "</orderId>";
		xml += "<orderName>" + this.getOrderName() + "</orderName>";
		xml += "<price>" + this.getPrice() + "</price>";
		xml += "</orderPriceInfo>";
		return xml;
	}
	public void parseFromXml(Element element) {
		// TODO Auto-generated method stub
		this.setCompId(Integer.parseInt(element.elementText("compId")));
		this.setCompName(element.elementText("compName"));
		this.setOrderId(Integer.parseInt(element.elementText("orderId")));
		this.setOrderName(element.elementText("orderName"));
		this.setPrice(Float.parseFloat(element.elementText("price")));
	}
}
