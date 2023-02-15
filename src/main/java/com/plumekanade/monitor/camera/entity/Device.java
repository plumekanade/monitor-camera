package com.plumekanade.monitor.camera.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备表
 *
 * @author kanade
 * @date 2023-02-15
 */
@Data
@TableName("device")
public class Device implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;
  @TableId
  private String id;
  /**
   * 名称
   */
  private String name;
  /**
   * 设备ip地址
   */
  private String host;
  /**
   * 端口号
   */
  private Integer port;
  /**
   * 视频流地址（路径）
   */
  private String streamUrl;
  /**
   * 视频编码（编号）
   */
  private String code;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
}
