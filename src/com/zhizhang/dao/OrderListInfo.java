package com.zhizhang.dao;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class OrderListInfo {
	private int id;
	private String name;
	private List<Float> priceList;
	private String imagePath;
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
	
	public List<Float> getPriceList() {
		return priceList;
	}
	public void setPriceList(List<Float> priceList) {
		this.priceList = priceList;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public void parseData(Element child) {
		this.id = Integer.parseInt(child.attributeValue("id"));
		this.name = child.attributeValue("name");
		this.imagePath = child.attributeValue("image");
		String price = child.attributeValue("price");
		String[] list = price.split("-");
		this.priceList = new ArrayList<Float>();
		for(String p : list){
			priceList.add(Float.parseFloat(p));
		}
	}
}
