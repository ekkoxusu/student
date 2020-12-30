package com.su.student.mybatisplugins.config.filter.web;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 处理get的日期
 * @author xusu
 * @since 2020/7/24
 */
@ControllerAdvice
public class GlobalControllerAdviceConfig {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ISO_DATE));
            }
        });
        webDataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDateTime.parse(text, DateTimeFormatter.ofPattern(WebMvcConfig.YYYY_MM_DD_HH_MM_SS)));
            }
        });
        webDataBinder.registerCustomEditor(LocalTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalTime.parse(text, DateTimeFormatter.ofPattern(WebMvcConfig.HH_MM_SS)));
            }
        });
        webDataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WebMvcConfig.YYYY_MM_DD_HH_MM_SS) {
                    @Override
                    public Date parse(String source) {
                        try {
                            return super.parse(source);//支持解析指定pattern类型。
                        } catch (Exception e) {
                            try {
                                return StdDateFormat.instance.parse(source);//支持解析long类型的时间戳
                            } catch (ParseException e1) {
                                throw new RuntimeException("日期格式非法：" + e1.getMessage());
                            }
                        }
                    }
                };
                try {
                    setValue(simpleDateFormat.parse(text));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
