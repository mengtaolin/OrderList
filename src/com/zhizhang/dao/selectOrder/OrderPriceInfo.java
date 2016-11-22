package com.zhizhang.dao.selectOrder;

import org.dom4j.Element;

import com.zhizhang.interfaces.XmlInterface;

public class OrderPriceInfo implements XmlInterface{
	/**
	 * 餐饮提供公司ID
	 */
	private int compId;
	/**
	 * 餐饮提供公司名称
	 */
	private String compName;
	/**
	 * 食品ID
	 */
	private int orderId;
	/**
	 * 食品名称
	 */
	private String orderName;
	/**
	 * 食品价格
	 */
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
