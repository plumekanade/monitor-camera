package com.plumekanade.monitor.camera.config;

import com.plumekanade.monitor.camera.consts.ProjectConst;
import com.plumekanade.monitor.camera.entity.Device;
import com.plumekanade.monitor.camera.entity.SystemConfig;
import com.plumekanade.monitor.camera.enums.SystemCodeEnum;
import com.plumekanade.monitor.camera.service.DeviceService;
import com.plumekanade.monitor.camera.service.SystemConfigService;
import com.plumekanade.monitor.camera.utils.MapperUtils;
import com.plumekanade.monitor.camera.utils.RtspUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import java.util.List;
import java.util.Map;

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
  private final SystemConfigService systemConfigService;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Map<SystemCodeEnum, SystemConfig> map = systemConfigService.getLabelMapValue();
    ProjectConst.DURATION = Long.parseLong(map.get(SystemCodeEnum.RECORD_DURATION).getValue());
    ProjectConst.VIDEO_SUFFIX = map.get(SystemCodeEnum.VIDEO_SUFFIX).getValue();
    ProjectConst.VIDEO_PATH = map.get(SystemCodeEnum.VIDEO_PATH).getValue();
    ProjectConst.RTSP_PREFIX_URL = map.get(SystemCodeEnum.RTSP_PREFIX_URL).getValue();
    ProjectConst.RTSP_SUFFIX_URL = map.get(SystemCodeEnum.RTSP_SUFFIX_URL).getValue();

//    List<Device> devices = deviceService.list();
//    if (devices != null && !devices.isEmpty()) {
//      for (Device device : devices) {
//        taskExecutor.execute(() -> {
//          try {
//            rtspUtils.getRecorder(device.getCode(), ProjectConst.RTSP_URL);
//          } catch (Exception e) {
//            log.error("【RTSP】解析设备 {} - {} 的流失败, 异常堆栈: ", device.getName(), device.getCode(), e);
//          }
//        });
//      }
//    }
  }
}
