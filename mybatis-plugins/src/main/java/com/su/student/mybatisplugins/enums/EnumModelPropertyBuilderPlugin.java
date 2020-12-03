package com.su.student.mybatisplugins.enums;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import springfox.documentation.builders.ModelPropertyBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author xusu
 * @data 2020年12月03日
 */
@Component
public class EnumModelPropertyBuilderPlugin implements ModelPropertyBuilderPlugin {

    @Override
    public void apply(ModelPropertyContext context) {
        Optional<BeanPropertyDefinition> optional = context.getBeanPropertyDefinition();
        if (!optional.isPresent()) {
            return;
        }

        final Class<?> fieldType = optional.get().getField().getRawType();

        addDescForEnum(context, fieldType);
    }

    private void addDescForEnum(ModelPropertyContext context, Class<?> fieldType) {
        if(BaseEnum.class.isAssignableFrom(fieldType)){
            BaseEnum[] enumConstants = (BaseEnum[]) fieldType.getEnumConstants();
            List<String> displayValues =
                    Arrays.stream(enumConstants)
                            .filter(Objects::nonNull)
                            .map(item -> item.getValue() + ":" + item.getDesc()).collect(Collectors.toList());
            ModelPropertyBuilder builder = context.getBuilder();
            // 强行访问私有属性，添加文本
            Field descField = ReflectionUtils.findField(builder.getClass(), "description");
            ReflectionUtils.makeAccessible(descField);
            String joinText = ReflectionUtils.getField(descField, builder)
                    + " (" + String.join("、", displayValues) + ")";

            builder.description(joinText).type(context.getResolver().resolve(Integer.class));
        }
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }

}
