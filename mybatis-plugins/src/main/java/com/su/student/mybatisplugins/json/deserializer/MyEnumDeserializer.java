package com.su.student.mybatisplugins.json.deserializer;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.su.student.mybatisplugins.util.EnumUtil;
import lombok.Data;

import java.io.IOException;

@Data
public class MyEnumDeserializer extends JsonDeserializer<Enum<?>> implements ContextualDeserializer {
    private Class<com.su.student.mybatisplugins.enums.DelEnum> clz;

    @Override
    public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
        if(StrUtil.isEmpty(jsonParser.getText())){
            return null;
        }
        return EnumUtil.getEnmu(jsonParser.getIntValue(), clz);
    }

    /**
     * 获取合适的解析器，把当前解析的属性Class对象存起来，以便反序列化的转换类型，为了避免线程安全问题，每次都new一个（通过threadLocal来存储更合理）
     * @param ctx
     * @param property
     * @return
     * @throws JsonMappingException
     */
    @Override
    public JsonDeserializer<Enum<?>> createContextual(DeserializationContext ctx, BeanProperty property) throws JsonMappingException {
        Class rawCls = ctx.getContextualType().getRawClass();
        MyEnumDeserializer clone = new MyEnumDeserializer();
        clone.setClz(rawCls);
        return clone;
    }
}
