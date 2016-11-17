package com.zhizhang.interfaces;

import org.dom4j.Element;

public interface XmlInterface {
	public void parseFromXml(Element root);
	public String toXml();

}
