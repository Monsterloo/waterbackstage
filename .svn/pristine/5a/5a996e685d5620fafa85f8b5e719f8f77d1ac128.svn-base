package com.hhh.fund.waterwx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hhh.fund.util.StringUtils;

public class DateUtils {
	
	//12小时制的，则使用hh:mm:ss 如果希望格式化时间为24小时制的，则使用HH:mm:ss
	private static SimpleDateFormat fmt_14a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	private static SimpleDateFormat fmt_8a = new SimpleDateFormat("yyyy-MM-dd"); 
	
	private static SimpleDateFormat fmt_8b = new SimpleDateFormat("yyyy/MM/dd");
	
	private static SimpleDateFormat fmt_15a = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
	
	private static SimpleDateFormat fmt_xx =  new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	
	private static SimpleDateFormat fmt_11date=new SimpleDateFormat("yyyy年MM月dd日");
	
	private static SimpleDateFormat fmt_12mmdd=new SimpleDateFormat("MM月dd日");
	
	public static Date StringToDate_15a(String timStr){
		if(StringUtil.isBlank(timStr)){
			return null;
		}
		Date date = new Date();
		try {
			date = fmt_15a.parse(timStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static Date StringToDate_8a(String timStr){
		if(StringUtil.isBlank(timStr)){
			return null;
		}
		Date date = new Date();
		try {
			date = fmt_8a.parse(timStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static String DateToString_8a(Date timeDate){
		if(timeDate == null){
			return "";
		}
		return fmt_8a.format(timeDate);
	}
	
	//2016-11-01 20:24:20  转成Date
	public static Date StringToDate_14a(String timStr){
		if(StringUtil.isBlank(timStr)){
			return null;
		}
		Date date = null;
		try {
			date = fmt_14a.parse(timStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	/**
	 * 获取当前日期减去七天
	 * @param datetime
	 * @return
	 */
	public static  String StringToDateTime_yMdhms(int num){
		String datetime="";
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - num);
		try {
			datetime = fmt_8a.format(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datetime;
	}
	/**
	 * Date转成2016-11-01 20:24:20 
	 * @param timeDate
	 * @return
	 */
	public static String DateToString_14a(Date timeDate){
		if(timeDate == null){
			return "";
		}
		return fmt_14a.format(timeDate);
	}

	/**
	 * 将时间转换成***小时/分钟前
	 * @param createTimeDate
	 * @return
	 */
	public static String changeToTimeDesc(Date createTimeDate){
		long totalSec = System.currentTimeMillis()-createTimeDate.getTime();
		long min = totalSec/1000/60;
		long hour = totalSec/1000/60/60;
		long day = totalSec/1000/60/60/24;
		if(min==0){
			return totalSec%60+"秒前";
		}
		if(min<60){
			return min+"分钟前";
		}
		if(hour<24){
			return hour+"小时前";
		}
		if(day<2){
			return "昨天";
		}
		return DateUtils.DateToString_14a(createTimeDate);
	}
	
	/**
	 * 将时间转换成***小时/分钟前
	 * @param createTime
	 * @return
	 */
	public static String changeToTimeDesc(String createTime) {
		if(StringUtil.isBlank(createTime)){
			return "";
		}
		if("2016-11-20 17:36:39.0".length()==createTime.length()){
			return changeToTimeDesc(StringToDate_15a(createTime));
		}
		if("yyyy-MM-dd HH:mm:ss".length()==createTime.length()){
			return changeToTimeDesc(StringToDate_14a(createTime));
		}
		return "";
	}

	
	public static void main(String[] args) {
		System.out.println(fmt_xx.format(new Date()));
	}
	/**
	 * 2016年11月11日 11:12:12
	 * @param complainDate
	 * @return
	 */
	public static String changeToTimeDesc_CN(String complainDate) {
		String dateStr = "";
		if(StringUtils.isBlank(complainDate)){
			return dateStr;
		}
		try {
			if("2016-11-20 17:36:39.0".length()==complainDate.length()){
				dateStr = fmt_xx.format(fmt_15a.parse(complainDate));
			}
			if("yyyy-MM-dd HH:mm:ss".length()==complainDate.length()){
				dateStr=fmt_xx.format(fmt_14a.parse(complainDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
		
	}
	/**
	 * 2016年11月11日
	 * @param complainDate
	 * @return
	 */
	public static String changeToTimeDesc_11date(String complainDate) {
		String dateStr = "";
		if(StringUtils.isBlank(complainDate)){
			return dateStr;
		}
		try {
			if("2016-11-20 17:36:39.0".length()==complainDate.length()){
				dateStr = fmt_11date.format(fmt_15a.parse(complainDate));
			}
			if("yyyy-MM-dd HH:mm:ss".length()==complainDate.length()){
				dateStr=fmt_11date.format(fmt_14a.parse(complainDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	/**
	 * 2016年11月11日
	 * @param complainDate
	 * @return
	 */
	public static String changeToTimeDesc_12mmdd(String complainDate) {
		String dateStr = "";
		if(StringUtils.isBlank(complainDate)){
			return dateStr;
		}
		try {
			if("2016-11-20 17:36:39.0".length()==complainDate.length()){
				dateStr = fmt_11date.format(fmt_15a.parse(complainDate));
			}
			if("yyyy-MM-dd HH:mm:ss".length()==complainDate.length()){
				dateStr=fmt_12mmdd.format(fmt_14a.parse(complainDate));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateStr;
	}
	public static String changeToTimeDesc_CN(Date complainDate){
		return changeToTimeDesc_CN(DateToString_14a(complainDate));
	}
	
	
	
	public static String getDaysAgo_Str(int days){
		return DateToString_14a(getDaysAgo_Date(days));
	}
	/**
	 * 获取*天之前凌晨0点的时间
	 * @param days
	 * @return
	 */
	public static Date getDaysAgo_Date(int days){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE,0);
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}

	
}
