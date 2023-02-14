package com.plumekanade.monitor.camera.utils;

import com.plumekanade.monitor.camera.consts.ProjectConst;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264;

/**
 * RTSP工具类
 *
 * @author kanade
 * @date 2023-02-01
 */
@Slf4j
@Component
public class RtspUtils {

  /**
   * 创建ffmpeg录像示例
   *
   * @param streamName 流名称, 用于保存流视频时区分
   */
  public void getRecorder(String streamName, String rtspUrl) throws Exception {
    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(rtspUrl);
    grabber.start();
    while (true) {
      if (grabber.getImageHeight() <= 0 || grabber.getImageWidth() <= 0) {
        continue;
      }
      Frame frame = grabber.grabFrame();
      if (frame == null) {
        log.error("【FFmpeg】获取流数据失败, frame为空, 结束 {} 流解析", streamName);
        break;
      }
      if (frame.imageHeight <= 0 || frame.imageWidth <= 0) {
        continue;
      }
      // 创建视频文件
      String filename = streamName + ProjectConst.UNDERLINE + ProjectConst.DTF.format(LocalDateTime.now()) + ProjectConst.POINT + ProjectConst.VIDEO_SUFFIX;
      File file = new File(ProjectConst.VIDEO_PATH + filename);
      if (!file.createNewFile()) {
        throw new Exception("创建视频文件失败");
      }
      // 1代表录制音频 0不录制
      FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(file, frame.imageWidth, frame.imageHeight, 1);
      recorder.setVideoCodec(AV_CODEC_ID_H264);   // 解码方式
      recorder.setFormat(ProjectConst.VIDEO_SUFFIX);  // 视频格式
      recorder.setFrameRate(60);  // 帧数
      recorder.start();
      long startTime = SystemClock.now();  // 开始时间 秒
      log.info("【FFmpeg】流 {} 开始保存视频到本地...", streamName);
      while (SystemClock.now() <= (startTime + ProjectConst.DURATION * 1000) && frame != null) {
        System.out.println(Arrays.toString(frame.image));
        recorder.record(frame);
        frame = grabber.grabFrame();
      }
      if (frame != null) {
        recorder.record(frame);
      }
      // 一个视频录完，重新开启解析
      grabber.stop();
      grabber.start();
      recorder.stop();
      log.info("【FFmpeg】流 {} 开始下一个视频录制...", streamName);
    }
  }

}
