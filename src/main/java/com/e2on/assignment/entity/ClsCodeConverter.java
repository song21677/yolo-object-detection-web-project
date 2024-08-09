package com.e2on.assignment.entity;

import javax.persistence.AttributeConverter;

public class ClsCodeConverter implements AttributeConverter<ClsCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClsCode attribute) {

        return attribute.getValue();
    }

    @Override
    public ClsCode convertToEntityAttribute(Integer dbData) {

        return ClsCode.ofValue(dbData);
    }
}
