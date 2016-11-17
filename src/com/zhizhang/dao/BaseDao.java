package com.zhizhang.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import org.dom4j.Element;

import com.zhizhang.interfaces.XmlInterface;

public class BaseDao implements XmlInterface {

	@Override
	public void parseFromXml(Element root) {
		// TODO Auto-generated method stub
		try {
			Field[] fields = getClass().getFields();
			for(Field field : fields){
				String fieldName = field.getName();
				String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Type type = field.getGenericType();
				Object value = root.elementText(fieldName);
				switch(type.getTypeName()){
				case "class java.lang.String":
					getClass().getMethod(setMethodName, String.class).invoke(this, value);
					break;
				case "class java.lang.Integer":
					getClass().getMethod(setMethodName, Integer.class).invoke(this, value);
					break;
				case "clase java.lang.Float":
					getClass().getMethod(setMethodName, Float.class).invoke(this, value);
					break;
				case "class java.lang.Boolean":
					setMethodName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					getClass().getMethod(setMethodName, Boolean.class).invoke(this, value);
					break;
				default:
					getClass().getMethod(setMethodName, Object.class).invoke(this, value);
					break;
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toXml() {
		// TODO Auto-generated method stub
		return null;
	}

}
