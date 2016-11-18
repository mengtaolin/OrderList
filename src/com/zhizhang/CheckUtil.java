package com.zhizhang;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class CheckUtil {
	
	public static Map<String,String> timeMap 	= null;
	private static int breakfastStartH 		= 0;
	private static int breakfastStartM 		= 0;
	private static int breakfastEndH 			= 0;
	private static int breakfastEndM 			= 0;
	private static int lunchStartH 			= 0;
	private static int lunchStartM 			= 0;
	private static int lunchEndH 				= 0;
	private static int lunchEndM 				= 0;
	private static int dinnerStratH 			= 0;
	private static int dinnerStratM 			= 0;
	private static int dinnerEndH 				= 0;
	private static int dinnerEndM 				= 0;
	
	public static void init(Map<String,String> map){
		timeMap 			= map;
		breakfastStartH 	= Integer.parseInt(timeMap.get("breakfastStartH"));
		breakfastStartM 	= Integer.parseInt(timeMap.get("breakfastStartM"));
		breakfastEndH 		= Integer.parseInt(timeMap.get("breakfastEndH"));
		breakfastEndM 		= Integer.parseInt(timeMap.get("breakfastEndM"));
		lunchStartH 		= Integer.parseInt(timeMap.get("lunchStartH"));
		lunchStartM 		= Integer.parseInt(timeMap.get("lunchStartM"));
		lunchEndH 			= Integer.parseInt(timeMap.get("lunchEndH"));
		lunchEndM 			= Integer.parseInt(timeMap.get("lunchEndM"));
		dinnerStratH 		= Integer.parseInt(timeMap.get("dinnerStratH"));
		dinnerStratM 		= Integer.parseInt(timeMap.get("dinnerStratM"));
		dinnerEndH 			= Integer.parseInt(timeMap.get("dinnerEndH"));
		dinnerEndM 			= Integer.parseInt(timeMap.get("dinnerEndM"));
	}
	
	@SuppressWarnings("deprecation")
	public static boolean checkTime(){
		if(timeMap == null)return false;
		Date date = new Date(System.currentTimeMillis());
		if(breakfastStartH > breakfastEndH){
			return false;
		}else if(breakfastStartH == breakfastEndH && breakfastStartM >= breakfastEndM){
			return false;
		}
		if(lunchStartH > lunchEndH){
			return false;
		}else if(lunchStartH == lunchEndH && lunchStartM >= lunchEndM){
			return false;
		}
		if(dinnerStratH > dinnerEndH){
			return false;
		}else if(dinnerStratH == dinnerEndH && dinnerStratM >= dinnerEndM){
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
	 * 返回早上，中午，还是晚上
	 * @return
	 */
	public static String timeType(){
		String str = "";
		Date date = new Date(System.currentTimeMillis());
		
		@SuppressWarnings("deprecation")
		int curHour = date.getHours();
		@SuppressWarnings("deprecation")
		int curMinutes = date.getMinutes();
		if(breakfastStartH == curHour){
			if(breakfastStartM <= curMinutes){
				str = "早餐";
			}
		}
		if(breakfastEndH == curHour){
			if(breakfastEndM > curMinutes){
				str = "早餐";
			}
		}
		
		if(lunchStartH == curHour){
			if(lunchStartM <= curMinutes){
				str = "午餐";
			}
		}
		if(lunchEndH == curHour){
			if(lunchEndM > curMinutes){
				str = "午餐";
			}
		}
		if(dinnerStratH == curHour){
			if(dinnerStratM <= curMinutes){
				str = "晚餐";
			}
		}
		if(dinnerEndH == curHour){
			if(dinnerEndM > curMinutes){
				str = "晚餐";
			}
		}
		
		return str;
	}
	
	public static String orderTime(){
		String str = "";
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
