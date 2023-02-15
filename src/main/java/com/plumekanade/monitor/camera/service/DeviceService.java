package com.plumekanade.monitor.camera.service;

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
}
