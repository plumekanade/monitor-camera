package com.plumekanade.monitor.camera.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.monitor.camera.entity.Device;
import com.plumekanade.monitor.camera.mapper.DeviceMapper;
import org.springframework.stereotype.Service;

/**
 * @author kanade
 * @date 2023-02-15
 */
@Service
public class DeviceService extends ServiceImpl<DeviceMapper, Device> {

  /**
   * 根据编号获取设备
   */
  public Device getDevice(String code) {
    return baseMapper.selectOne(Wrappers.lambdaQuery(Device.class).eq(Device::getCode, code));
  }
}
