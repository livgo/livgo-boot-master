package com.livgo.boot.data.datasource.druid;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Description:连接池配置参数对象
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
//@ConfigurationProperties(prefix = "druid",locations = "classpath:jdbc.properties", ignoreUnknownFields = false)
@Component
@ConfigurationProperties(prefix = "druid")
@PropertySource("classpath:jdbc.properties")
@Data
public class DruidPoolDataSourceProperties {

    private String jdbcDriver;
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private int jdbcInitialPoolSize;
    private int jdbcMaxActive;
    private int jdbcMaxIdle;
    private int jdbcMinIdle;
    private int jdbcMaxWait;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean testWhileIdle;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private boolean removeAbandoned;
    private int removeAbandonedTimeout;
    private boolean logAbandoned;
    private String validationQuery;

}