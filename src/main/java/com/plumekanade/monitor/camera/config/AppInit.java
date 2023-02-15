package com.plumekanade.monitor.camera.config;

import com.plumekanade.monitor.camera.consts.ProjectConst;
import com.plumekanade.monitor.camera.entity.Device;
import com.plumekanade.monitor.camera.service.DeviceService;
import com.plumekanade.monitor.camera.utils.RtspUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import java.util.List;

/**
 * @author kanade
 * @date 2023-02-01
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class AppInit implements ApplicationRunner {

  private final RtspUtils rtspUtils;
  private final TaskExecutor taskExecutor;
  private final DeviceService deviceService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    List<Device> devices = deviceService.list();
    if (devices != null && !devices.isEmpty()) {
      for (Device device : devices) {
        taskExecutor.execute(() -> {
          try {
            rtspUtils.getRecorder(device.getCode(), ProjectConst.RTSP_URL);
          } catch (Exception e) {
            log.error("【RTSP】解析设备 {} - {} 的流失败, 异常堆栈: ", device.getName(), device.getCode(), e);
          }
        });
      }
    }
  }
}
