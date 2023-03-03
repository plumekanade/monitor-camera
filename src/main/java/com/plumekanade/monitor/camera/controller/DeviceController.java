package com.plumekanade.monitor.camera.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.plumekanade.monitor.camera.entity.Device;
import com.plumekanade.monitor.camera.service.DeviceService;
import com.plumekanade.monitor.camera.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kanade
 * @date 2023-03-03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/device")
public class DeviceController {
  private final DeviceService deviceService;

  /**
   * 获取设备列表
   */
  @GetMapping("/getList")
  public Result getList() {
    return Result.success(deviceService.list(Wrappers.lambdaQuery(Device.class).orderByAsc(Device::getSort).orderByAsc(Device::getId)));
  }
}
