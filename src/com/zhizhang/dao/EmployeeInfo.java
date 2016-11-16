package com.zhizhang.dao;

import java.util.Date;

import org.dom4j.Element;

public class EmployeeInfo {
	/**
	 * 配置中的ID
	 */
	private int id;
	/**
	 * 配置中的名字
	 */
	private String name;
	/**
	 * 用于后期扩展为身份证ID
	 */
	private String uid;
	/**
	 * 身份证照片地址
	 */
	private String[] idImgUrl;
	/**
	 * 头像地址
	 */
	private String imgUrl;
	/**
	 * 出生地
	 */
	private String bornPlace;
	/**
	 * 生日
	 */
	private Date birthDay;
	/**
	 * 电话号码
	 */
	private String telNum;
	/**
	 * qq号码
	 */
	private String qqNum;
	/**
	 * 教育程度
	 */
	private String education;
	/**
	 * 第一联系人
	 */
	private String firstContact;
	/**
	 * 第一联系人关系
	 */
	private String firstContactRelation;
	/**
	 * 第一联系人电话号码
	 */
	private String firstContactNum;
	/**
	 * 第二联系人
	 */
	private String secondContact;
	/**
	 * 第二联系人关系
	 */
	private String secondContactRelation;
	/**
	 * 第二联系人电话号码
	 */
	private String secondContactNum;
	/**
	 * 所属部门
	 */
	private String department;
	/**
	 * 所在职位
	 */
	private String position;
	/**
	 * 入职时间
	 */
	private Date enterTime;
	/**
	 * 是否住宿
	 */
	private boolean isLiveHere;
	/**
	 * 是否为正式员工
	 */
	private boolean isRegularEmployee;

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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String[] getIdImgUrl() {
		return idImgUrl;
	}

	public void setIdImgUrl(String[] idImgUrl) {
		this.idImgUrl = idImgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getBornPlace() {
		return bornPlace;
	}

	public void setBornPlace(String bornPlace) {
		this.bornPlace = bornPlace;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getFirstContact() {
		return firstContact;
	}

	public void setFirstContact(String firstContact) {
		this.firstContact = firstContact;
	}

	public String getFirstContactRelation() {
		return firstContactRelation;
	}

	public void setFirstContactRelation(String firstContactRelation) {
		this.firstContactRelation = firstContactRelation;
	}

	public String getFirstContactNum() {
		return firstContactNum;
	}

	public void setFirstContactNum(String firstContactNum) {
		this.firstContactNum = firstContactNum;
	}

	public String getSecondContact() {
		return secondContact;
	}

	public void setSecondContact(String secondContact) {
		this.secondContact = secondContact;
	}

	public String getSecondContactRelation() {
		return secondContactRelation;
	}

	public void setSecondContactRelation(String secondContactRelation) {
		this.secondContactRelation = secondContactRelation;
	}

	public String getSecondContactNum() {
		return secondContactNum;
	}

	public void setSecondContactNum(String secondContactNum) {
		this.secondContactNum = secondContactNum;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public boolean isLiveHere() {
		return isLiveHere;
	}

	public void setLiveHere(boolean isLiveHere) {
		this.isLiveHere = isLiveHere;
	}

	public boolean isRegularEmployee() {
		return isRegularEmployee;
	}

	public void setRegularEmployee(boolean isRegularEmployee) {
		this.isRegularEmployee = isRegularEmployee;
	}

	public void parsData(Element child) {
		// TODO Auto-generated method stub
		this.setId(Integer.parseInt(child.attributeValue("id")));
		this.setName(child.getText());
	}
}
