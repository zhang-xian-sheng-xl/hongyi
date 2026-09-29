package com.hongyi.hr.module.hy.Test;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 模拟Spring容器 全局只初始化一次
@Service
public class ThreadPoolDemo {

    // 指定使用我们配置的线程池
    @Async("commonTaskExecutor")
    public void sendSms(String phone) {
        // 短信、推送、日志、记录操作
        System.out.println("发送短信给：" + phone);
    }

    @Async("commonTaskExecutor")
    public Future<Integer> countBatch() {
        // 批量计算/数据库批量处理
        return CompletableFuture.completedFuture(100);
    }

    public static void main(String[] args) {

    }

}
