package com.plumekanade.monitor.camera.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Netty配置类
 *
 * @author kanade
 * @date 2023-02-14
 */
@Configuration
public class NettyConfig {
  @Value("${netty.boss}")
  private Integer boss;
  @Value("${netty.worker}")
  private Integer worker;
  @Value("${netty.timeout}")
  private Integer timeout;

  private final NettyInitializer nettyInitializer;

  public NettyConfig(NettyInitializer nettyInitializer) {
    this.nettyInitializer = nettyInitializer;
  }

  /**
   * 服务启动器对象
   */
  @Bean
  public ServerBootstrap serverBootstrap() {
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    serverBootstrap.group(new NioEventLoopGroup(boss), new NioEventLoopGroup(worker))
        .channel(NioServerSocketChannel.class)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout)
        .childHandler(nettyInitializer);
    return serverBootstrap;
  }
}
