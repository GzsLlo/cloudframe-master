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

public class MysqlDtransGenerator {

  private static final String host = "127.0.0.1:3306";
  private static final String database = "storage"; //account/storage/order
  private static final String url = "jdbc:mysql://" + host + "/" + database + "?characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
  private static final String userName = "root";
  private static final String password = "123456";

  private static final String module = "com.ai.cloudframe.provider.dtrans";
  //private static final String projectPath = "/cloudframe-provider/cloudframe-provider-dtrans/cloudframe-provider-dtrans-account";
  private static final String projectPath = "/cloudframe-provider/cloudframe-provider-dtrans/cloudframe-provider-dtrans-storage";
  //private static final String projectPath = "/cloudframe-provider/cloudframe-provider-dtrans/cloudframe-provider-dtrans-order";


  public static void main(String[] args) {
    CodeGeneratorTool generatorTool = new CodeGeneratorTool();
    generatorTool.generCode(host, database, url, userName, password, projectPath, module);
  }
}
