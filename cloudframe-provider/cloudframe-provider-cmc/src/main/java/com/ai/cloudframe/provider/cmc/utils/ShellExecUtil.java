/**
 * Copyright 2018 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.cmc.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 执行shell脚本.
 *
 * @Author:jiangchao
 * @CreateDate:2019/7/25
 */
public class ShellExecUtil {

  private static Logger logger = LoggerFactory.getLogger(ShellExecUtil.class);

  /**
   * 创建会话连接
   *
   * @param ip         主机IP
   * @param user       主机登陆用户名
   * @param pwd        主机登陆密码
   * @param port       主机ssh2登陆端口，如果取默认值，传-1
   * @param privateKey
   * @param passphrase
   */
  public static Session sshConnect(String ip, String user, String pwd, int port,
                                   String privateKey, String passphrase) throws Exception {
    //验证主机ip
    if (null == ip || "".equals(ip)) {
      logger.error("主机IP为空");
      throw new RuntimeException("主机IP为空");
    }

    //验证主机登陆用户名
    if (null == user || "".equals(user)) {
      logger.error("主机登陆用户名为空");
      throw new RuntimeException("主机登陆用户名为空");
    }

    //验证主机登陆密码
    if (null == pwd || "".equals(pwd)) {
      logger.error("主机登陆密码为空");
      throw new RuntimeException("主机登陆密码为空");
    }

    JSch jsch = new JSch();
    Session session = null;

    //设置密钥和密码
    if (privateKey != null && !"".equals(privateKey)) {
      if (passphrase != null && "".equals(passphrase)) {
        //设置带口令的密钥
        jsch.addIdentity(privateKey, passphrase);
      } else {
        //设置不带口令的密钥
        jsch.addIdentity(privateKey);
      }
    }

    if (port <= 0) {
      //连接服务器，采用默认端口
      session = jsch.getSession(user, ip);
    } else {
      //采用指定的端口连接服务器
      session = jsch.getSession(user, ip, port);
    }

    //如果服务器连接不上，则抛出异常
    if (null == session) {
      logger.error("ssh session is null");
      throw new Exception("ssh session is null");
    }

    //设置登陆主机的密码
    session.setPassword(pwd);//设置密码
    //设置第一次登陆的时候提示，可选值：(ask | yes | no)
    session.setConfig("StrictHostKeyChecking", "no");
    //设置登陆超时时间
    session.connect(10000);
    session.sendKeepAliveMsg();
    session.setServerAliveCountMax(10000);

    //返回会话
    return session;
  }


  /**
   * 关闭会话连接
   *
   * @param session
   */
  public static void sshDisconnect(Session session) throws Exception {
    if (null == session) {
      logger.error("ssh session is null,关闭session异常");
      throw new Exception("ssh session is null,关闭session异常");
    }
    session.disconnect();
  }


  /**
   * 执行ssh远程命令
   *
   * @param ip
   * @param user
   * @param pwd
   * @param port
   * @param privateKey
   * @param passphrase
   * @param command    执行命令
   * @param isGetMes   是否返回命令执行的结果
   */
  public static String sshExecCmd(String ip, String user, String pwd, int port,
                                  String privateKey, String passphrase, String command, boolean isGetMes) throws Exception {
    //获取ssh连接会话
    Session session = sshConnect(ip, user, pwd, port,
        privateKey, passphrase);

    if (null == session) {
      logger.error("创建ssh连接失败");
      throw new RuntimeException("创建ssh连接失败");
    }

    ChannelExec openChannel = null;

    openChannel = (ChannelExec) session.openChannel("exec");
    logger.debug("ShellExecUtil.sshExecCmd command : {}", command);
    openChannel.setCommand(command);
    int exitStatus = openChannel.getExitStatus();
    System.out.println(exitStatus);
    openChannel.connect(1000000);

    InputStream in = openChannel.getInputStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String buf = null;

    String result = "";
    while ((buf = reader.readLine()) != null && !"all server start".equals(buf)) {
      result += buf.toString() + System.lineSeparator();
      ;//new String(buf.getBytes("gbk"),"UTF-8") + "\n";
    }
    logger.info(result);
    //System.out.println(result);

    //断开连接
    openChannel.disconnect();
    sshDisconnect(session);

    if (isGetMes) {
      return result;
    } else {
      return "";
    }
  }
}
