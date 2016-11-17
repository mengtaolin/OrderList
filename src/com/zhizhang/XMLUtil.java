package com.zhizhang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import com.zhizhang.interfaces.XmlInterface;

public class XMLUtil {
	
	public void parseXml(Object obj){
		
	}
	
	public boolean add(Object obj){
		return false;
	}
	
	public boolean delete(Object obj){
		return false;
	}
	
	public boolean update(Object obj){
		return false;
	}
	
	public Object select(Object obj){
		return null;
	}
	
	public void create(XmlInterface xmlObj,String path){
		
		try {
			File file =new File(path);
			boolean exist = file.exists();
	        if(exist == false){       
	            file.createNewFile();
	        } 
	        FileWriter writer = new FileWriter(file);
	        writer.write(xmlObj.toXml());
	        writer.flush();
	        writer.close();
	        writer = null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
