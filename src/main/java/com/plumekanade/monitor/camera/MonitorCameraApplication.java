package com.plumekanade.monitor.camera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
//@MapperScan("com.plumekanade.monitor.camera.mapper")
@EnableTransactionManagement(proxyTargetClass = true)   // 开启事务管理, 不写Service接口层, 声明基于类代理(CGlib)
public class MonitorCameraApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonitorCameraApplication.class, args);
  }

}
