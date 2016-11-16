package com.zhizhang.dao;

public class SelectOrderInfo {
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
}
