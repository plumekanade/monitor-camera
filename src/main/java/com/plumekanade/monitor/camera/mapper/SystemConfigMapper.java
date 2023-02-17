package com.plumekanade.monitor.camera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.plumekanade.monitor.camera.entity.SystemConfig;
import com.plumekanade.monitor.camera.enums.SystemCodeEnum;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author kanade
 * @date 2023-02-15
 */
@Mapper
public interface SystemConfigMapper extends BaseMapper<SystemConfig> {
  /**
   * label为键，value为值
   */
  @MapKey("code")
  @Select("select * from system_config")
  Map<SystemCodeEnum, SystemConfig> getLabelMapValue();
}
