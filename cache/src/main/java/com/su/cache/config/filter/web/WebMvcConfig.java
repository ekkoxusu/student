package com.su.cache.config.filter.web;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author xusu
 * @since 2020/12/2
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String HH_MM_SS = "HH:mm:ss";

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        // 如果日期格式化默认失败则使用jackSon默认日期处理格式、可处理格式详见StdDateFormat.ALL_FORMATS
        /**
         * 序列换成json时,将所有的long变成string
         */
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS)));
        simpleModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
        simpleModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(HH_MM_SS)));
        simpleModule.addSerializer(Date.class, new DateSerializer(false,new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS){
            @Override
            public Date parse(String source) {
                try {
                    return super.parse(source);//支持解析指定pattern类型。
                } catch (Exception e) {
                    try {
                        return StdDateFormat.instance.parse(source);//支持解析long类型的时间戳
                    } catch (ParseException e1) {
                        throw new RuntimeException("日期格式非法：" + e);
                    }
                }
            }
        }));
        // 解决前端Long类型可能存在的精度丢失问题
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        //配置反序列化格式
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(
                YYYY_MM_DD_HH_MM_SS)));
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
        simpleModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(HH_MM_SS)));

        simpleModule.addDeserializer(Date.class,
                new DateDeserializers.DateDeserializer(DateDeserializers.DateDeserializer.instance, new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS){
                    @Override
                    public Date parse(String source) {
                        try {
                            return super.parse(source);//支持解析指定pattern类型。
                        } catch (Exception e) {
                            try {
                                return StdDateFormat.instance.parse(source);//支持解析long类型的时间戳
                            } catch (ParseException e1) {
                                throw new RuntimeException("日期格式非法：" + e);
                            }
                        }
                    }
                },YYYY_MM_DD_HH_MM_SS));
        converters.parallelStream()
                //过滤出MappingJackson2HttpMessageConverter
                .filter(httpMessageConverter -> httpMessageConverter instanceof MappingJackson2HttpMessageConverter)
                //将所有MappingJackson2HttpMessageConverter中的objectMapper替换成自定义的策略
                .forEach(httpMessageConverter -> ((MappingJackson2HttpMessageConverter) httpMessageConverter).getObjectMapper().registerModule(simpleModule).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }

}
