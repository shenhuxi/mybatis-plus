package com.baomidou.mybatisplus.samples.quickstart.mybatisconfig;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.update.Update;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author By ZengPeng
 * @Description  mybatis 的配置
 * @date in  2020/3/30 10:23
 */
@Component
public class MybatisConfig {

    /**
    * @Description  攻击 SQL 阻断解析器
    * @return  com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
    **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        // 攻击 SQL 阻断解析器、加入解析链
        sqlParserList.add(new BlockAttackSqlParser() {
            @Override
            public void processDelete(Delete delete) {
                // 如果你想自定义做点什么，可以重写父类方法像这样子
                if ("user".equals(delete.getTable().getName())) {
                    // 自定义跳过某个表，其他关联表可以调用 delete.getTables() 判断
                    return ;
                }
                super.processDelete(delete);
            }
            @Override
            public void processUpdate(Update update) {
                // 如果你想自定义做点什么，可以重写父类方法像这样子
                if ("people_flow".equals(update.getTable().getName())) {
                    // 自定义跳过某个表，其他关联表可以调用 delete.getTables() 判断
                    //throw new RuntimeException("无权修改敏感表数据");
                }
                super.processUpdate(update);
            }
        });
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    /**
    * @Description  乐观锁
    * @return  com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor
    **/
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
