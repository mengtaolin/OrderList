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
	
	
	/**
	 * 传入timeType方法中，返回中文的早中晚餐
	 */
	public static int TIME_TYPE1 				= 1;
	/**
	 * 传入timeType方法中，返回英文的早中晚餐
	 */
	public static int TIME_TYPE2 				= 2;
	
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
		
		if(checkByTime(date, breakfastStartH, breakfastStartM, breakfastEndH, breakfastEndM) == false){
			if(checkByTime(date, lunchStartH, lunchStartM, lunchEndH, lunchEndM) == false){
				return checkByTime(date, dinnerStratH, dinnerStratM, dinnerEndH, dinnerEndM);
			}
			else{
				return true;
			}
		}else{
			return true;
		}
		
	}
	
	private static boolean checkByTime(Date date, int startH, int startM, int endH, int endM){
		int hour = date.getHours();
		int minutes = date.getMinutes();
		if(hour == startH){
			if(minutes >= startM){
				return true;
			}
			else{
				return false;
			}
		}else if(hour > startH){
			if(hour < endH){
				return true;
			}
			else if(hour == endH){
				if(minutes <= endM){
					return true;
				}
				return false;
			}else{
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 返回早上，中午，还是晚上
	 * @param type 1为中文，2为英文
	 * @return
	 */
	public static String timeType(int type){
		String str = "";
		Date date = new Date(System.currentTimeMillis());
		
		if(checkByTime(date, breakfastStartH, breakfastStartM, breakfastEndH, breakfastEndM)){
			str = type == 1 ? "早餐" : "breakfast";
		}else if(checkByTime(date, lunchStartH, lunchEndM, lunchEndH, lunchEndM)){
			str = type == 1 ? "午餐" : "lunch";
		}else if(checkByTime(date, dinnerStratH, dinnerStratM, dinnerEndH, dinnerEndM)){
			str = type == 1 ? "晚餐" : "dinner";
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
	 * 返回 y-M-d
	 * @return
	 */
	public static String getCurDate(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("y-M-d");
		String str = format.format(date);
		return str;
	}
	/**
	 * 返回当前服务器时间 h:m:s
	 * @return
	 */
	public static String getCurTime(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("h:m:s");
		String str = format.format(date);
		return str;
	}
	/**
	 * 返回 y年M月d日
	 * @return
	 */
	public static String getCurDate1(){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("y年M月d日");
		String str = format.format(date);
		return str;
	}
}
