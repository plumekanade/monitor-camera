package com.plumekanade.monitor.camera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * WebSocket返回视频图片给前端的结果
 *
 * @author kanade
 * @date 2023-04-15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocketResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String image;
    private String imageTime;
}
