package com.plumekanade.monitor.camera.consts;

import java.time.format.DateTimeFormatter;

/**
 * 项目常量类
 *
 * @author kanade
 * @date 2023-01-31
 */
public class ProjectConst {
  /**
   * 测试用rtsp视频流地址
   */
  public static final String RTSP_URL = "rtsp://localhost:554/live";
  /**
   * 单个视频录制时长 秒为单位
   */
//  public static final Long DURATION = 21600L;
  public static final Long DURATION = 60L;
  /**
   * 视频保存地址
   */
  public static final String VIDEO_PATH = "E:\\code\\";
  /**
   * 视频格式
   */
  public static final String VIDEO_SUFFIX = "mp4";
  /**
   * 下划线 _
   */
  public static final String UNDERLINE = "_";
  /**
   * 点 .
   */
  public static final String POINT = ".";
  public static final String VIDEO_NAME_TIME = "yyyy-MM-dd-HH_mm_ss";
  public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(VIDEO_NAME_TIME);
}
