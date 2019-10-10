package com.ai.cloudframe.web.Util;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String SEG_YYYYMMDD = "yyyy-MM-dd";


    public static Date string2Date(String str, String format) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        if (str==null || "".equals(str)) {
            date = new Date();
            return date;
        }
        try {
            String strdate = str.replace("Z", " UTC");//是空格+UTC
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            date = sdf1.parse(strdate);
//            date = formatter.parse(str);
        } catch (ParseException e) {
            logger.error("DateUtil error:{}",e);
        }
        return date;
    }

    public static Date stringforDate(String str, String format) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        if (str==null || "".equals(str)) {
            date = new Date();
            return date;
        }
        try {
            date = formatter.parse(str);
        } catch (ParseException e) {
            logger.error("DateUtil error:{}",e);
        }
        return date;
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Date date=Date.from(localDateTime.atZone(zoneId).toInstant());
        return  date;
    }


    public static String date2String(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String dateString = formatter.format(date);
        return dateString;
    }

    public static String date2String(Date date,String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * string2LocalDateTime.
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static LocalDateTime string2LocalDateTime(String dateStr, String format) {
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.parse(dateStr, df);
        return ldt;
    }

    public static int getAgeByBirth(Date birthDay) throws ParseException {
        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }


    /**
     * string2XmlByDom4j(当前版本不完整，需要改造).
     *
     * @param content
     * @return
     * @throws Exception
     */
    public static HashMap<String, Object> string2XmlByDom4j(String content) throws Exception {

        logger.debug("stringToXmlByDom4j content : {}", content);

        HashMap<String, Object> result = new HashMap();

        SAXReader saxReader = new SAXReader();
        Document docDom4j = saxReader.read(new ByteArrayInputStream(content.getBytes("utf-8")));
        Element root = docDom4j.getRootElement();

        getValue(root, result);
        return result;
    }

    /**
     * 遍历元素.
     *
     * @param element
     * @param map
     */
    private static void getValue(Element element, Map map) {
        List<Attribute> attributeList = element.attributes();
        for (Attribute attr : attributeList) {
            map.put(attr.getName(), attr.getValue());
        }
        List<Element> elementList = element.elements();
        for (Element ele : elementList) {
            List eleEleList = ele.elements();
            if (null != eleEleList && !eleEleList.isEmpty()) {
                getValue(ele, map);
            } else {
                List<Attribute> eleAttrList = ele.attributes();
                for (Attribute attr : eleAttrList) {
                    map.put(attr.getName(), attr.getValue());
                }
                map.put(ele.getName(), ele.getText());
            }
        }
    }


}
