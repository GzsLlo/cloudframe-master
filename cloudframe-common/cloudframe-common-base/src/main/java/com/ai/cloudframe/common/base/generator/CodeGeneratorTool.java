package com.ai.cloudframe.common.base.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>
 *  代码生成器工具
 * </p>
 *
 * @author tangsz
 * @since 2019-05-22
 */

public class CodeGeneratorTool {

  private  String host = "";
  private  String database = "";
  private  String url = "";
  private  String userName = "";
  private  String password = "";
  private  String driverName = "com.mysql.jdbc.Driver";
  private  String projectPath = System.getProperty("user.dir");

  private  String mapperXmlFile = "";


  public  void generCode(String host, String database, String url, String userName, String password, String projectPath,
                         String module) {
    this.host = host;
    this.database = database;
    this.url = url;
    this.userName = userName;
    this.password = password;
    this.projectPath = this.projectPath + projectPath;

    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    mpg.setGlobalConfig(initGlobalConfig());

    // 数据源配置
    mpg.setDataSource(initDataSourceConfig());

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(scanner("模块名"));
    pc.setModuleName("");
    pc.setParent("");
    pc.setController(module + ".controller");
    pc.setEntity(module + ".entity");
    pc.setMapper(module + ".mapper");
    pc.setService(module + ".service");
    pc.setServiceImpl(module + ".service.ipml");

    mpg.setPackageInfo(pc);

    // 自定义配置

    mpg.setCfg(initInjectionConfig());
    mpg.setTemplate(new TemplateConfig().setXml(null));

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    strategy.setEntityLombokModel(true);
    strategy.setSuperControllerClass("com.ai.cloudframe.common.base.support.BaseController");
    strategy.setInclude(scanner("表名"));
    strategy.setSuperEntityColumns("id");
    strategy.setTablePrefix(scanner("表前缀"));
    mpg.setStrategy(strategy);
    mpg.setTemplateEngine(new FreemarkerTemplateEngine());

    mpg.setTemplate(initTemplateConfig());
    mpg.execute();
  }


  /**
   * 全局配置
   * @return
   */
  private  GlobalConfig initGlobalConfig() {
    GlobalConfig gc = new GlobalConfig();
    gc.setOutputDir(projectPath + "/src/main/java");
    gc.setAuthor("Automated procedures");
    gc.setOpen(false);
    return gc;
  }


  /**
   * 配置数据源
   * @return
   */
  private  DataSourceConfig initDataSourceConfig() {
    return new DataSourceConfig()
            .setUrl(url)
            .setDriverName(driverName)
            .setUsername(userName)
            .setPassword(password);
  }

  /**
   * 自定义配置
   * @return
   */
  private  InjectionConfig initInjectionConfig() {
    InjectionConfig cfg = new InjectionConfig() {
      @Override
      public void initMap() {
        // to do nothing
      }
    };
    List<FileOutConfig> focList = new ArrayList<>();
    focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
      @Override
      public String outputFile(TableInfo tableInfo) {
        // 自定义输入文件名称
        mapperXmlFile = projectPath + "/src/main/resources/mapper/" + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
        return projectPath + "/src/main/resources/mapper/" + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
      }
    });
    cfg.setFileOutConfigList(focList);
    return cfg;
  }

  /**
   * 覆盖Entity以及xml
   * @return
   */
  private  TemplateConfig initTemplateConfig() {
    TemplateConfig tc = new TemplateConfig();
    tc.setXml(null);
    //如果当前Entity已经存在,那么仅仅覆盖Entity
    File file = new File(mapperXmlFile);
    if (file.exists()) {
      tc.setController(null);
      tc.setMapper(null);
      tc.setService(null);
      tc.setServiceImpl(null);
      tc.setEntityKt(null);
    }
    return tc;
  }

  /**
   * <p>
   * 读取控制台内容
   * </p>
   */
  public  String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    StringBuilder help = new StringBuilder();
    help.append("请输入" + tip + "：");
    System.out.println(help.toString());
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotEmpty(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }

}
