package com.zhizhang.dao;

import java.util.ArrayList;
import java.util.List;

public class CompanyDataInfo {
	public String name = "广州市志展汽车配件有限公司";
	public List<DepartmentInfo> departments = new ArrayList<DepartmentInfo>();
	
	public DepartmentInfo getDepartmentByName(String departName){
		if(departments == null){
			return null;
		}
		else{
			for(DepartmentInfo department : departments){
				if(department.getName() == departName)
				{
					return department;
				}
			}
			return null;
		}
	}
	
	public DepartmentInfo getDepartmentById(int departId){
		if(departments == null){
			return null;
		}
		else{
			for(DepartmentInfo department : departments){
				if(department.getId() == departId)
				{
					return department;
				}
			}
			return null;
		}
	}
}
