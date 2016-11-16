package com.zhizhang.dao;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class DepartmentInfo {
	private int id;
	private String name;
	private List<EmployeeInfo> children;
	
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
	
	public List<EmployeeInfo> getChildren() {
		return children;
	}
	
	public void setChildren(List<EmployeeInfo> children) {
		this.children = children;
	}
	
	public EmployeeInfo getEmployeeInfoById(int employeeId){
		if(this.children == null)return null;
		if(this.children.size() == 0)return null;
		for(EmployeeInfo info : children){
			if(info.getId() == employeeId){
				return info;
			}
		}
		return null;
	}
	
	public void parsData(Element element) {
		this.id = Integer.parseInt(element.attributeValue("id"));
		this.name = element.attributeValue("name");
		@SuppressWarnings("unchecked")
		List<Element> elementChildren = element.elements();
		children = new ArrayList<EmployeeInfo>();
		for(Element child : elementChildren){
			EmployeeInfo employee = new EmployeeInfo();
			employee.parsData(child);
			children.add(employee);
		}
	}
}
