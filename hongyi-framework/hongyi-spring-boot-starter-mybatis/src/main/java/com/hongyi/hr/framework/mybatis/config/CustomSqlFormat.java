package com.hongyi.hr.framework.mybatis.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class CustomSqlFormat implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        // 只返回 SQL 语句及其参数
        // 移除 SQL 语句中的换行符
//        将多个空格转为单个空格
        if (sql != null) {
            sql = sql.replaceAll("[\\r\\n]+", " ");
            sql = sql.replaceAll("\\s+", " ");
        }
        return now+"  耗时："+elapsed+"毫秒 "+sql;
    }
}
