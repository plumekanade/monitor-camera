package com.plumekanade.monitor.camera.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.Buffer;
import java.nio.charset.StandardCharsets;

/**
 * Netty处理器
 *
 * @author kanade
 * @date 2023-02-14
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyHandler extends SimpleChannelInboundHandler<Buffer[]> {

  /**
   * 客户端连接对象, 用于发送数据
   */
  private static ChannelHandlerContext CONTEXT = null;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    log.info("【Netty】客户端 {} 连接...", ctx.channel().remoteAddress().toString());
    // 如果处于连接状态则手动关闭
    try {
      if (CONTEXT != null && CONTEXT.channel().isActive()) {
        ctx.channel().close();
      }
    } catch (Exception e) {
      log.error("【Netty】关闭原连接失败, 直接替换原连接, 异常堆栈: ", e);
    }
    CONTEXT = ctx;
    super.channelActive(ctx);
    ctx.channel().writeAndFlush("恭喜连接成功".getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("【Netty】客户端 {} 断开...", ctx.channel().remoteAddress().toString());
    super.channelInactive(ctx);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, Buffer[] buffer) throws Exception {

  }
}
