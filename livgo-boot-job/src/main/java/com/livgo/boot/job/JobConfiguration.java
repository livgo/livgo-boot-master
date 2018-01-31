package com.livgo.boot.job;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;

/**
 * Description: Job 配置
 * Author:     gaocl
 * Date:       2018/1/26
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Configuration
public class JobConfiguration implements AsyncConfigurer, SchedulingConfigurer {

    @Value("${job.poolSize}")
    private int poolSize;
    @Value("${job.threadNamePrefix}")
    private String threadNamePrefix;
    @Value("${job.awaitTerminationSeconds}")
    private int awaitTerminationSeconds;
    @Value("${job.waitForTasksToCompleteOnShutdown}")
    private boolean waitForTasksToCompleteOnShutdown;


    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(poolSize);
        scheduler.setThreadNamePrefix(threadNamePrefix);
        scheduler.setAwaitTerminationSeconds(awaitTerminationSeconds);
        scheduler.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        return scheduler;
    }

    @Override
    public Executor getAsyncExecutor() {
        return this.taskScheduler();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        TaskScheduler scheduler = this.taskScheduler();
        registrar.setTaskScheduler(scheduler);
    }
}
