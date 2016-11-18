package com.zhizhang.dao.selectOrder;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.dom4j.Element;

import com.zhizhang.interfaces.XmlInterface;

@XmlRootElement
public class SelectOrderInfo implements XmlInterface{
	private int departmentId;
	private int employeeId;
	private String department;
	private String employee;
	private OrderPriceInfo[] orderPriceInfo;
	
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public OrderPriceInfo[] getOrderPriceInfo() {
		return orderPriceInfo;
	}
	public void setOrderPriceInfo(OrderPriceInfo[] orderPriceInfo) {
		this.orderPriceInfo = orderPriceInfo;
	}
	
	public float getTotalPrice(){
		int price = 0;
		for(OrderPriceInfo info : orderPriceInfo){
			price += info.getPrice();
		}
		return price;
	}
	public void parseFromXml(Element root){
		this.setDepartment(root.elementText("department"));
		this.setDepartmentId(Integer.parseInt(root.elementText("departmentId")));
		this.setEmployee(root.elementText("employee"));
		this.setEmployeeId(Integer.parseInt(root.elementText("employeeId")));
		Element priceElement = root.element("orderPriceInfo");
		@SuppressWarnings("unchecked")
		List<Element> list = priceElement.elements();
		int len = list.size();
		OrderPriceInfo[] orderPriceList = new OrderPriceInfo[len];
		for(int i=0;i < len;i ++){
			OrderPriceInfo info = new OrderPriceInfo();
			info.parseFromXml(list.get(i));
			orderPriceList[i] = info;
		}
		this.setOrderPriceInfo(orderPriceList);
	}
	
	public String toXml(){
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
		xml += "<selectOrderInfo>";
		xml += "<department>" + this.getDepartment() + "</department>";
		xml += "<departmentId>" + this.getDepartmentId() + "</departmentId>";
		xml += "<employee>" + this.getEmployee() + "</employee>";
		xml += "<employeeId>" + this.getEmployeeId() + "</employeeId>";
		for(OrderPriceInfo info : this.orderPriceInfo){
			xml += info.toXml();
		}
		xml += "</selectOrderInfo>";
		return xml;
	}
}
