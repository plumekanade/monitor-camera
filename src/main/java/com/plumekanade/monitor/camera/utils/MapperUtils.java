package com.plumekanade.monitor.camera.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 序列化/反序列化工具类
 *
 * @author kanade
 * @version 1.0
 * @date 2021-01-21 09:41
 */
@Slf4j
public class MapperUtils {

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    // 在序列化时日期格式默认为 yyyy-MM-dd HH:mm:ss
    MAPPER.getSerializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    // 在序列化时忽略值为 null 的属性
    MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    // 指定序列化字段顺序
    MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    // 在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
    MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    // 格式化输出
    MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
    // 忽略无法转换的对象
    MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    JavaTimeModule dateTimeModule = new JavaTimeModule();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    dateTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dtf));
    dateTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dtf));
    MAPPER.registerModule(dateTimeModule).registerModule(new ParameterNamesModule());

    JavaTimeModule dateModule = new JavaTimeModule();
    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    dateModule.addSerializer(LocalDate.class, new LocalDateSerializer(df));
    dateModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(df));
    MAPPER.registerModule(dateModule).registerModule(new ParameterNamesModule());
  }

  /**
   * 将实体类对象转换成json字符串
   */
  public static String serialize(Object object) {

    try {
      if (null != object) {
        return MAPPER.writeValueAsString(object);
      }
    } catch (Exception e) {
      log.error("Jackson转换String异常: ", e);
    }
    return null;
  }

  /**
   * 反序列化(带有unicode)
   */
  public static <T> T deserialize(InputStream in, TypeReference<T> typeReference) {
    try {
      return MAPPER.readValue(in, typeReference);
    } catch (Exception e) {
      log.error("Jackson转换String异常: ", e);
    }
    return null;
  }

  /**
   * 将json字符串转换成对应的实体类对象
   */
  public static <T> T deserialize(String json, Class<T> tClass) {
    try {
      if (StringUtils.isNotBlank(json)) {
        return MAPPER.readValue(json, tClass);
      }
    } catch (Exception e) {
      log.error("Jackson转换 {} 类异常: ", tClass, e);
    }
    return null;
  }

  /**
   * 将json字符串转换成对应的实体类对象
   */
  public static <T> T deserialize(String json, TypeReference<T> typeReference) {
    try {
      if (StringUtils.isNotBlank(json)) {
        return MAPPER.readValue(json, typeReference);
      }
    } catch (Exception e) {
      log.error("Jackson转换 {} 类异常: ", typeReference.getClass(), e);
    }
    return null;
  }

}
