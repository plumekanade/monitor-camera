package com.plumekanade.monitor.camera.config;

import com.plumekanade.monitor.camera.consts.ProjectConst;
import com.plumekanade.monitor.camera.utils.RtspUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

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

  @Override
  public void run(ApplicationArguments args) throws Exception {
    taskExecutor.execute(() -> {
      try {
        rtspUtils.getRecorder("stream01", ProjectConst.RTSP_URL);
      } catch (Exception e) {
        log.error("【RTSP】解析流 stream01 失败, 异常堆栈: ", e);
      }
    });
  }
}
