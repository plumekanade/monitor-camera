package com.plumekanade.monitor.camera.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.monitor.camera.entity.SystemConfig;
import com.plumekanade.monitor.camera.mapper.SystemConfigMapper;
import org.springframework.stereotype.Service;

/**
 * @author kanade
 * @date 2023-02-15
 */
@Service
public class SystemConfigService extends ServiceImpl<SystemConfigMapper, SystemConfig> {
}
