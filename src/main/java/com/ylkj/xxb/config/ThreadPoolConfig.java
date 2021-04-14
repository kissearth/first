package com.ylkj.xxb.config;

import com.ylkj.xxb.util.concurrent.ConcurrentExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean
    @Primary
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    @Primary
    public ConcurrentExecutor concurrentExecutor() {
        ConcurrentExecutor concurrentExecutor = new ConcurrentExecutor();
        concurrentExecutor.setPoolSize(100);
        concurrentExecutor.initialize();
        return concurrentExecutor;
    }

}
