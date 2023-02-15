package com.plumekanade.monitor.camera.config;

import com.plumekanade.monitor.camera.handler.NettyHandler;
import com.plumekanade.monitor.camera.mapper.DeviceMapper;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Netty通道初始化
 *
 * @author kanade
 * @date 2023-02-14
 */
@Component
@AllArgsConstructor
public class NettyInitializer extends ChannelInitializer<SocketChannel> {

  private final NettyHandler nettyHandler;

  /**
   * 初始化通道
   */
  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    ChannelPipeline pipeline = socketChannel.pipeline();
    pipeline.addLast(new ByteArrayDecoder());
    pipeline.addLast(new ByteArrayEncoder());
    pipeline.addLast(nettyHandler);
  }
}
