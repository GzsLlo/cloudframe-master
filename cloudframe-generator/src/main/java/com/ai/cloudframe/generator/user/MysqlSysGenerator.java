package com.ai.cloudframe.generator.user;


import com.ai.cloudframe.common.base.generator.CodeGeneratorTool;

/**
 * <p>
 *  系统管理代码生成器
 * </p>
 *
 * @author tangsz
 * @since 2019-05-22
 */

public class MysqlSysGenerator {

  private static final String host = "121.42.237.56:3306";
  private static final String database = "cmc_dev";
  private static final String url = "jdbc:mysql://" + host + "/" + database + "?characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
  private static final String userName = "pension";
  private static final String password = "Pension123.";

  private static final String module = "com.ai.cloudframe.provider.sys";
  private static final String projectPath = "/cloudframe-provider/cloudframe-provider-sys";


  public static void main(String[] args) {
    CodeGeneratorTool generatorTool = new CodeGeneratorTool();
    generatorTool.generCode(host, database, url, userName, password, projectPath, module);
  }
}
