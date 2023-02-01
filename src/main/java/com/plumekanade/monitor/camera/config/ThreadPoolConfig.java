package com.plumekanade.monitor.camera.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author kanade
 * @date 2023-02-01
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class ThreadPoolConfig {

  @Bean
  public TaskExecutor threadTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    // 线程池的线程最少数量
    executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
    // 最大数量
    executor.setMaxPoolSize(20);
    // 队列数
    executor.setQueueCapacity(1000);
    executor.setThreadNamePrefix("custom-task-executor");
    // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.initialize();
    return executor;
  }
}
