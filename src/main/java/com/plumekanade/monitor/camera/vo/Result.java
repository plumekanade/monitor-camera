package com.plumekanade.monitor.camera.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author kanade
 * @date 2023-03-03
 */
@Data
@AllArgsConstructor
public class Result implements Serializable {
  @Serial
  private static final long serialVersionUID = 8722479616313943839L;
  private Integer code;
  private String msg;
  private Object data;

  public static Result success(Object data) {
    return new Result(0, "ok", data);
  }

  public static Result error(String msg) {
    return new Result(-1, msg, null);
  }
}
