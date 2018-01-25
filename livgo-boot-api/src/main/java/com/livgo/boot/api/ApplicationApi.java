package com.livgo.boot.api;

import com.livgo.boot.data.datasource.druid.DruidPoolDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Configuration
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DruidPoolDataSourceProperties.class)
@ComponentScan({"com.livgo.*"})
@MapperScan("com.livgo")
public class ApplicationApi extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationApi.class, "org.springframework.web.context.ContextLoaderListener");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationApi.class);
    }

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {

            @Override
            public void contextInitialized(ServletContextEvent sce) {
                System.setProperty("config.path.redis", this.getClass().getResource("/").getPath() + "redis.properties");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {

            }
        };
    }
}
