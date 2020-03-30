package com.baomidou.mybatisplus.samples.quickstart.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 *  演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 *
 * @author czchen
 * @since 2020-03-23
 */
public class CodeGenerator {

    /**
     * 配置模块与表
     */
    private static Map<String,List<String>> moduleTableMap = new HashMap<>();

    static{
        //key为当前所有文件放置的包名，如果为空指的就是packageConfig父类目录下，value为在该包名下生成的表，可以多张表，存放在list
        moduleTableMap.put("", Arrays.asList("people_flow"));
    }

    public static void main(String[] args) {
        autoGenerator();
    }

    private static void autoGenerator(){
        if(!moduleTableMap.isEmpty()){
            for(Map.Entry<String,List<String>> entry : moduleTableMap.entrySet()){
                List<String> tables = entry.getValue();
                if(CollectionUtils.isNotEmpty(tables)){
                    tables.stream().forEach(e->{
                        // 代码生成器
                        AutoGenerator mpg = new AutoGenerator();
                        //全局配置
                        String projectPath = globalConfig(mpg);
                        //数据库配置
                        dataSourceConfig(mpg);
                        //包配置
                        PackageConfig pc =packageConfig(mpg,entry.getKey());
                        injectionConfig(mpg,pc,projectPath);
                        strategyConfig(mpg,pc,e);
                        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
                        mpg.execute();
                    });
                }
            }
        }else{
            throw new RuntimeException("请配置模块");
        }
    }

    /**
     * 设置全局配置.
     *
     * @param mpg 自动代码生成对象
     * @return 设置全局配置
     */
    private static String globalConfig(AutoGenerator mpg){
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zengpeng");
        gc.setOpen(false);
        gc.setDateType(DateType.ONLY_DATE);
        gc.setIdType(IdType.UUID);
        mpg.setGlobalConfig(gc);
        return projectPath;
    }

    /**
     * 数据源配置
     * @param mpg 自动代码生成对象
     */
    private static void dataSourceConfig(AutoGenerator mpg){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/netcommand?characterEncoding=utf-8&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("admin");
        dsc.setPassword("admin123");
        mpg.setDataSource(dsc);
    }

    /**
     * 设置包配置.
     *
     * @param mpg 自动代码生成对象
     * @param moduleName 当前包的名字
     * @return 包的配置
     */
    private static PackageConfig packageConfig(AutoGenerator mpg,String moduleName){
        PackageConfig pc = new PackageConfig();
        //模块名
        pc.setModuleName(moduleName);//com.baomidou.mybatisplus.samples.quickstart
        pc.setParent("com.baomidou.mybatisplus.samples.quickstart");
        mpg.setPackageInfo(pc);
        return pc;
    }

    /**
     * 自定义配置
     *
     * @param mpg 自动代码生成对象
     * @param pc 包配置
     * @param projectPath 设置全局配置
     */
    private static void injectionConfig(AutoGenerator mpg,PackageConfig pc,String projectPath){
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
                String outPath = projectPath+"/src/main/java/"+pc.getParent().replaceAll("\\.","/")
                        +"/mapper/"+tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                // 自定义输入文件名称
//                outPath = projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                return outPath;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));
    }

    /**
     * 策略配置
     *
     * @param mpg 自动代码生成对象
     * @param pc 包配置
     * @param tableName 表格名字
     */
    private static void strategyConfig(AutoGenerator mpg, PackageConfig pc, String tableName){
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("com.sucl.sbmp.core.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.sucl.sbmp.core.web.BaseController");
        strategy.setInclude(tableName);
        //strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
    }

}