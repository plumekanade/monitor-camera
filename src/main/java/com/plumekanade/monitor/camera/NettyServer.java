package com.plumekanade.monitor.camera;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Netty服务
 *
 * @author kanade
 * @date 2023-02-14
 */
@Slf4j
@Configuration
public class NettyServer {
  @Value("${netty.port}")
  private Integer port;

  private final ServerBootstrap serverBootstrap;
  private ChannelFuture channelFuture = null;

  public NettyServer(ServerBootstrap serverBootstrap) {
    this.serverBootstrap = serverBootstrap;
  }

  /**
   * 启动Netty服务
   */
  @PostConstruct
  public void start() {
    try {
      channelFuture = serverBootstrap.bind(port).sync();
      log.info("【Netty】端口为 {} 的 Socket 服务启动完成...", port);
    } catch (Exception e) {
      log.error("【Netty】启动 Socket 服务失败, 异常堆栈: ", e);
    }
  }

  /**
   * 关闭服务
   */
  @PreDestroy
  public void close() {
    try {
      if (channelFuture != null) {
        channelFuture.channel().close();
      }
    } catch (Exception e) {
      log.error("【Netty】关闭 Socket 服务失败, 异常堆栈: ", e);
    }
  }
}
