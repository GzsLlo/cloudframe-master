package com.ai.cloudframe.common.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String DD = "dd";
	public static final String MM = "MM";
	public static final String YYYYMM = "yyyyMM";
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMMDDHH = "yyyyMMddHH";
	public static final String YYYYMMDD_HHMMSS = "yyyyMMddHHmmss";
	public static final String YYYYMMDD_HHMMSS_SSS = "yyyyMMddHHmmssSSS";
	public static final String SEG_YYYYMMDD = "yyyy-MM-dd";
	public static final String SEG_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String SEG_YYYYMMDD_HHMMSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String SEG_YYYY_MM_DD_HHMMSS = "yyyy/MM/dd HH:mm:ss";
	
	private DateUtil(){
	}
	public static String getCurrentDateTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	public static String getCurrentDateTime(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/// 秒差
	public static long diffSeconds(Date endDate, Date beginDate) {
		return (endDate.getTime() - beginDate.getTime()) / 1000;
	}

	/// 毫秒差
	public static long diffMilliSeconds(Date endDate, Date beginDate) {
		return (endDate.getTime() - beginDate.getTime());
	}

	/// 当前绝对毫秒数
	public static long nowAbsMilliSeconds() {
		return System.currentTimeMillis();
	}

	/// 当前绝对秒数
	public static long nowAbsSeconds() {
		return System.currentTimeMillis() / 1000;
	}

	public static Date string2Date(String str, String format) {
		Date date = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		if (StringUtil.isEmpty(str)) {
			date = new Date();
			return date;
		}
		
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {
			logger.error("parse error:"+e);
		}
		return date;
	}

	public static Date string2Date(String str) {
		return string2Date(str, DateUtil.SEG_YYYYMMDD_HHMMSS_SSS);
	}

	public static String addseconds(String inYYYYMMDD_HHMMSS, long addsecs) {
		Date date = string2Date(inYYYYMMDD_HHMMSS, DateUtil.YYYYMMDD_HHMMSS);
		if (null == date) {
			return null;
		}else{
			date.setTime(date.getTime() + addsecs * 1000);
			return getCurrentDateTime(date, DateUtil.YYYYMMDD_HHMMSS);
		}
	}
	
	public static Date sysDate() {
		return new Date();
	}

	public static synchronized Date addMonths(Date time,int months){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.MONTH,months);
		return calendar.getTime();
	}
	
	public static synchronized String addMonths(String time,int months,String format){
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		Date date = null;
		try {
			date = sf.parse(time);
		} catch (ParseException e) {
			logger.error("ERROR: error={}", e);
		}
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,months);
		return sf.format(calendar.getTime());
	}
	
	/**
	 * 判断是否为闲时
	 * 
	 * @param time
	 * @return
	 */
	public static String isTimeIdle(String time) {
		long valTime = Long.valueOf(time);
		if (valTime >= 230000 && valTime <= 240000 || valTime >= 0 && valTime <= 70000) {
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 用年月时间规则遍历前4位的数值字串
	 *
	 * @param
	 * @return
	 */
	public static List<String> traversalDate12Bit() {
		List<String> dateStr12 = new ArrayList<>();
		for (int year=0; year<=99; year++) {
			for (int month=1; month<=12; month++) {
				String timestr = String.format("%02d%02d", year, month);
				dateStr12.add(timestr);
			}
		}
		return dateStr12;
	}
}
