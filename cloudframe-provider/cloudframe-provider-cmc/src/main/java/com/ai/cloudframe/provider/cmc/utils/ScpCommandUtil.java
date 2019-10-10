/**
 * Copyright 2018 asiainfo Inc.
 **/

package com.ai.cloudframe.provider.cmc.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import com.ai.cloudframe.provider.api.sys.model.vo.DeployVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * 使用scp命令下载上传文件.
 *
 * @Author:jiangchao
 * @CreateDate:2019/7/25
 */
public class ScpCommandUtil {

  private static Logger logger = LoggerFactory.getLogger(ScpCommandUtil.class);

  /**
   * 使用scp命令下载上传文件.
   *
   * @param deployVo
   * @throws IOException
   */
  public static void scpCommand(DeployVo deployVo) throws IOException {
    logger.debug("ScpCommandUtil.scpCommand deployVo : {}", deployVo);
//
//    //文件服务器地址
//    Connection originConn = new Connection(deployVo.getOriginalIp());
//    originConn.connect();
//    boolean isAuthenticated =
//        originConn.authenticateWithPassword(deployVo.getOriginalUser(), deployVo.getOriginalPwd());
//
//    if (isAuthenticated == false) {
//      throw new IOException("original Authentication failed.");
//    }
//
//    SCPClient originClient = new SCPClient(originConn);
//    //get方法用来将目标服务器的文件下载到本地服务器
//    originClient.get(deployVo.getOriginalUrl(), System.getProperty("user.dir"));
//    originConn.close();
//    logger.debug("ScpCommandUtil.scpCommand get file success");

    //目标服务器地址
    Connection targetConn = new Connection(deployVo.getTargetIp());
    targetConn.connect();

    boolean isAuthenticated =
        targetConn.authenticateWithPassword(deployVo.getTargetUser(), deployVo.getTargetPwd());
    if (isAuthenticated == false) {
      throw new IOException("target Authentication failed.");
    }
    SCPClient targetClient = new SCPClient(targetConn);

    String fileName = deployVo.getOriginalUrl();
//    String[] strs = fileName.split("/");
//    fileName = strs[strs.length - 1];
    //put方法用来将本地文件上传到目标服务器
    targetClient.put(fileName, deployVo.getTargetUrl());
    targetConn.close();
    logger.debug("ScpCommandUtil.scpCommand put file success");

    File file = new File(System.getProperty("user.dir") + File.separator + fileName);
    if (null != file) {
      file.delete();
    }

    logger.debug("ScpCommandUtil.scpCommand end");
  }
}
