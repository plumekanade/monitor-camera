package com.plumekanade.monitor.camera.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.plumekanade.monitor.camera.enums.SystemCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统配置表
 *
 * @author kanade
 * @date 2023-02-15
 */
@Data
@TableName("system_config")
public class SystemConfig implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  @TableId
  private String id;
  /**
   * 配置属性的编码
   */
  private SystemCodeEnum code;
  /**
   * 名称
   */
  private String label;
  /**
   * 配置的值
   */
  private String value;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
}
