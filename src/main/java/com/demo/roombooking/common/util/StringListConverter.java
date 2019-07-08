package com.demo.roombooking.common.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName StringListConvertor
 * @Description 用于实体映射中将List<String> 转换成 String
 * @Author BeatMercy
 * @Date 2019/7/2 17:09
 * @Version 1.0
 */

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> stringList) {
        if (stringList == null) return null;
        return String.join(SPLIT_CHAR, stringList);
    }

    @Override
    public List<String> convertToEntityAttribute(String string) {
        if (string == null) return null;
        return Arrays.asList(string.split(SPLIT_CHAR));
    }
}