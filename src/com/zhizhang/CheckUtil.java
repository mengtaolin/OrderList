package com.zhizhang;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class CheckUtil {
	public static Map<String,String> timeMap = null;
	
	public static boolean checkTime(){
		if(timeMap == null)return false;
		int lunchStartH = Integer.parseInt(timeMap.get("lunchStartH"));
		int lunchStartM = Integer.parseInt(timeMap.get("lunchStartM"));
		int lunchEndH = Integer.parseInt(timeMap.get("lunchEndH"));
		int lunchEndM = Integer.parseInt(timeMap.get("lunchEndM"));
		Date date = new Date(System.currentTimeMillis());
		if(lunchStartH > lunchEndH){
			return false;
		}else if(lunchStartH == lunchEndH && lunchStartM >= lunchEndM){
			return false;
		}
		
		if(date.getHours() == lunchStartH){
			if(date.getMinutes() >= lunchStartM){
				return true;
			}
			else{
				return false;
			}
		}else if(date.getHours() == lunchEndH){
			if(date.getMinutes() <= lunchEndM){
				return true;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * 杩斿洖鏃╀笂锛屼腑鍗堬紝杩樻槸鏅氫笂
	 * @return
	 */
	public static String timeType(){
		String str = "鏃╅";
		Date date = new Date(System.currentTimeMillis());
		int breakfastStartH = Integer.parseInt(timeMap.get("breakfastStartH"));
		int breakfastStartM = Integer.parseInt(timeMap.get("breakfastStartM"));
		int breakfastEndH = Integer.parseInt(timeMap.get("breakfastEndH"));
		int breakfastEndM = Integer.parseInt(timeMap.get("breakfastEndM"));
		int lunchStartH = Integer.parseInt(timeMap.get("lunchStartH"));
		int lunchStartM = Integer.parseInt(timeMap.get("lunchStartM"));
		int lunchEndH = Integer.parseInt(timeMap.get("lunchEndH"));
		int lunchEndM = Integer.parseInt(timeMap.get("lunchEndM"));
		int dinnerStratH = Integer.parseInt(timeMap.get("dinnerStratH"));
		int dinnerStratM = Integer.parseInt(timeMap.get("dinnerStratM"));
		int dinnerEndH = Integer.parseInt(timeMap.get("dinnerEndH"));
		int dinnerEndM = Integer.parseInt(timeMap.get("dinnerEndM"));
		
		int curHour = date.getHours();
		int curMinutes = date.getMinutes();
		if(breakfastStartH == curHour){
			if(breakfastStartM <= curMinutes){
				str = "鏃╅";
			}
		}
		if(breakfastEndH == curHour){
			if(breakfastEndM > curMinutes){
				str = "鏃╅";
			}
		}
		
		if(lunchStartH == curHour){
			if(lunchStartM <= curMinutes){
				str = "鍗堥";
			}
		}
		if(lunchEndH == curHour){
			if(lunchEndM > curMinutes){
				str = "鍗堥";
			}
		}
		if(dinnerStratH == curHour){
			if(dinnerStratM <= curMinutes){
				str = "鏅氶";
			}
		}
		if(dinnerEndH == curHour){
			if(dinnerEndM > curMinutes){
				str = "鏅氶";
			}
		}
		
		return str;
	}
	
	public static String orderTime(){
		String str = "";
		int lunchStartH = Integer.parseInt(timeMap.get("lunchStartH"));
		int lunchStartM = Integer.parseInt(timeMap.get("lunchStartM"));
		int lunchEndH = Integer.parseInt(timeMap.get("lunchEndH"));
		int lunchEndM = Integer.parseInt(timeMap.get("lunchEndM"));
		str = lunchStartH + ":" + lunchStartM + "至" + lunchEndH + ":" + lunchEndM;
		return str;
	}
	
	public static String getPropValue(String key){
		String value = timeMap.get(key);
		return value;
	}
	
	/**
	 * 返回 **-**-**
	 * @return
	 */
	public static String getCurDate(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("y-M-d");
		String str = format.format(date);
		return str;
	}
	
	public static String getCurDate1(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("y年M月d日");
		String str = format.format(date);
		return str;
	}
}
