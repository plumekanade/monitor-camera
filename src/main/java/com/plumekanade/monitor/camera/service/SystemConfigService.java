package com.plumekanade.monitor.camera.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.plumekanade.monitor.camera.entity.SystemConfig;
import com.plumekanade.monitor.camera.enums.SystemCodeEnum;
import com.plumekanade.monitor.camera.mapper.SystemConfigMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author kanade
 * @date 2023-02-15
 */
@Service
public class SystemConfigService extends ServiceImpl<SystemConfigMapper, SystemConfig> {

  /**
   * label为键，value为值
   */
  public Map<SystemCodeEnum, SystemConfig> getLabelMapValue() {
    return baseMapper.getLabelMapValue();
  }
}
