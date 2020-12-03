package com.su.student.mybatisplugins.json.serializer;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.su.student.mybatisplugins.enums.BaseEnum;
import lombok.Data;

import java.io.IOException;

/**
 * @author xusu
 */
@Data
public class MyEnumSerializer extends JsonSerializer<BaseEnum> implements ContextualSerializer {

    @Override
    public void serialize(BaseEnum anEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // 或者使用FastJSON解决一下问题
        JSONObject jsonObject = new JSONObject();
        jsonObject.set("desc",anEnum.getDesc());
        jsonObject.set("value",anEnum.getValue());
        jsonGenerator.writeString(jsonObject.toString());
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        return this;
    }



}
