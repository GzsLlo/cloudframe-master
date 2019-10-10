/**
 * Copyright 2019 asiainfo Inc.
 **/

package com.ai.cloudframe.common.base.util;

import com.ai.cloudframe.common.base.constant.GlobalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: shenbin
 * @CreateDate: [2019/7/20 17:18]
 * @Version: [v1.0]
 */
public class InterfaceUtil {

  private static Logger logger = LoggerFactory.getLogger(InterfaceUtil.class);

  public static String doPost(String path,String data,String contantType,String requestType) {
    String result="";
    try {
      URL url = new URL(path);
      //打开和url之间的连接
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      PrintWriter out = null;
      //请求方式
      if(GlobalConstant.REQUEST_METHOD.POST.equals(requestType)){
        conn.setRequestMethod("POST");
      }else if(GlobalConstant.REQUEST_METHOD.GET.equals(requestType)){
        conn.setRequestMethod("GET");
      }

//           //设置通用的请求属性
      conn.setRequestProperty("accept", "*/*");
      conn.setRequestProperty("connection", "Keep-Alive");
      conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
      if(GlobalConstant.API_DATA_FORMAT.JSON.equals(contantType)){
        conn.setRequestProperty("Content-Type", "application/json");
      }else if(GlobalConstant.API_DATA_FORMAT.XML.equals(contantType)){
        conn.setRequestProperty("Content-Type", "application/xml");
      }

      //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
      //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
      //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
      conn.setDoOutput(true);
      conn.setDoInput(true);
      //获取URLConnection对象对应的输出流
      out = new PrintWriter(conn.getOutputStream());
      //发送请求参数即数据
      out.print(data);
      //缓冲数据
      out.flush();
      //获取URLConnection对象对应的输入流
      InputStream is = conn.getInputStream();
      //构造一个字符流缓存
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      String line = br.readLine();
      StringBuffer sb = new StringBuffer(line);
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
      result = sb.toString();
      //关闭流
      is.close();
      //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
      //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
      conn.disconnect();

    } catch (Exception e) {
      logger.error("get Telit rate plan Err :{}",e);
    }
    return result;
  }
}
