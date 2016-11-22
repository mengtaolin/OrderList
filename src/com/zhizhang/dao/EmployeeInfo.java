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
	 * 性别
	 */
	private String genderStr;
	/**
	 * 性别 0女 1男
	 */
	private int gender;
	/**
	 * 用于后期扩展为身份证ID
	 */
	private String uid;
	/**
	 * 身份证正面照片地址
	 */
	private String idImgUrlFront;
	/**
	 * 身份证背面照片
	 */
	private String idImgUrlBack;
	/**
	 * 民族
	 */
	private String ethnic;
	/**
	 * 头像地址
	 */
	private String imgUrl;
	/**
	 * 出生地
	 */
	private String birthPlace;
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
	public String getGenderStr() {
		return genderStr;
	}
	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getIdImgUrlFront() {
		return idImgUrlFront;
	}
	public void setIdImgUrlFront(String idImgUrlFront) {
		this.idImgUrlFront = idImgUrlFront;
	}
	public String getIdImgUrlBack() {
		return idImgUrlBack;
	}
	public void setIdImgUrlBack(String idImgUrlBack) {
		this.idImgUrlBack = idImgUrlBack;
	}
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
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

	/**
	 * 将保存在xml中的详细资料读取出来
	 * @param root
	 */
	@SuppressWarnings("deprecation")
	public void parseAllInfo(Element root) {
		this.setId(Integer.parseInt(root.elementText("id")));
		this.setName(root.elementText("name")); 
		this.setGenderStr(root.elementText("genderStr"));
		this.setGender(Integer.parseInt(root.elementText("gender")));
		this.setUid(root.elementText("uid"));
		this.setBirthDay(new Date(root.elementText("birthDay")));
		this.setBirthPlace(root.elementText("birthPlace"));
		this.setDepartment(root.elementText("department"));
		this.setEducation(root.elementText("education"));
		this.setEnterTime(new Date(root.elementText("enterTime")));
		this.setFirstContact(root.elementText("firstContact"));
		this.setFirstContactNum(root.elementText("firstContactNum"));
		this.setFirstContactRelation(root.elementText("firstContactRelation"));
		this.setIdImgUrlFront(root.elementText("idImgUrlFront"));
		this.setIdImgUrlBack(root.elementText("idImgUrlBack"));
		this.setEthnic(root.elementText("ethnic"));
		this.setImgUrl(root.elementText("imgUrl"));
		this.setLiveHere(Boolean.parseBoolean(root.elementText("liveHere")));
		this.setPosition(root.elementText("position"));
		this.setQqNum(root.elementText("qqNum"));
		this.setRegularEmployee(Boolean.parseBoolean(root.elementText("regularEmployee")));
		this.setSecondContact(root.elementText("secondContact"));
		this.setSecondContactNum(root.elementText("secondContactNum"));
		this.setSecondContactRelation(root.elementText("secondContactRelation"));
		this.setTelNum(root.elementText("telNum"));
	}
	
	@SuppressWarnings("deprecation")
	public String parseToXml(){
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xml += "<root>";
		xml += "<id>" + this.getId() + "</id>";
		xml += "<name>" + this.getName() + "</name>";
		xml += "<genderStr>" + this.getGenderStr() + "<genderStr>";
		xml += "<gender>" + this.getGender() + "</gender>";
		xml += "<uid>" + this.getUid() + "</uid>";
		xml += "<birthDay>" + this.getBirthDay().getYear() + ":" + this.getBirthDay().getMonth() + ":" + this.getBirthDay().getDay() + "</birthDay>";
		xml += "<birthPlace>" + this.getBirthPlace() + "</birthPlace>";
		xml += "<department>" + this.getDepartment() + "</department>";
		xml += "<telNum>" + this.getTelNum() + "</telNum>";
		xml += "<education>" + this.getEducation() + "</education>";
		xml += "<enterTime>" + this.getEnterTime().getYear() + ":" + this.getEnterTime().getMonth() + ":" + this.getEnterTime().getDay() + "</enterTime>";
		xml += "<firstContact>" + this.getFirstContact() + "</firstContact>";
		xml += "<firstContactNum>" + this.getFirstContactNum() + "</firstContactNum>";
		xml += "<firstContactRelation>" + this.getFirstContactRelation() + "</firstContactRelation>";
		xml += "<idImgUrlFront>" + this.getIdImgUrlFront() + "</idImgUrlFront>";
		xml += "<idImgUrlBack>" + this.getIdImgUrlBack() + "</idImgUrlBack>";
		xml += "<ethnic>" + this.getEthnic() + "</ethnic>";
		xml += "<imgUrl>" + this.getImgUrl() + "</imgUrl>";
		xml += "<liveHere>" + this.isLiveHere + "</liveHere>";
		xml += "<position>" + this.getPosition() + "</position>";
		xml += "<qqNum>" + this.getQqNum() + "</qqNum>";
		xml += "<regularEmployee>" + this.isRegularEmployee + "</regularEmployee>";
		xml += "<secondContact>" + this.getSecondContact() + "</secondContact>";
		xml += "<secondContactNum>" + this.getSecondContactNum() + "</secondContactNum>";
		xml += "<secondContactRelation>" + this.getSecondContactRelation() + "</secondContactRelation>";
		xml += "</root>";
		return xml;
	}
}
