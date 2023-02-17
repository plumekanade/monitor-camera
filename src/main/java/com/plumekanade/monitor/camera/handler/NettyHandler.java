package com.plumekanade.monitor.camera.handler;

import com.plumekanade.monitor.camera.entity.Device;
import com.plumekanade.monitor.camera.service.DeviceService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Netty处理器
 *
 * @author kanade
 * @date 2023-02-14
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyHandler extends SimpleChannelInboundHandler<byte[]> {

  private final DeviceService deviceService;

  /**
   * 客户端连接对象, 用于发送数据
   */
  public static final Map<String, ChannelHandlerContext> CTX_MAP = new HashMap<>();
  private ChannelHandlerContext context = null;

  public NettyHandler(DeviceService deviceService) {
    this.deviceService = deviceService;
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    log.info("【Netty】客户端 {} 连接...", ctx.channel().remoteAddress().toString());
    // 如果处于连接状态则手动关闭
    try {
      if (context != null && context.channel().isActive()) {
        ctx.channel().close();
      }
    } catch (Exception e) {
      log.error("【Netty】关闭原连接失败, 直接替换原连接, 异常堆栈: ", e);
    }
    context = ctx;
    super.channelActive(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    log.info("【Netty】客户端 {} 断开...", ctx.channel().remoteAddress().toString());
    super.channelInactive(ctx);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, byte[] bytes) throws Exception {
    String code = new String(bytes);
    Device device = deviceService.getDevice(code);
    if (device != null) {
      CTX_MAP.put(code, ctx);
      log.info("【Netty】收到客户端 {} 关联编号 {} 设备的消息", ctx.channel().remoteAddress().toString(), code);
    } else {
      log.info("【Netty】收到客户端 {} 关联编号 {} 数据库不存在的设备的消息", ctx.channel().remoteAddress().toString(), code);
    }
  }
}
