package com.company.bsaadmin.util.converter;

import javax.persistence.AttributeConverter;

import com.company.bsaadmin.util.EnumValue;

public class GenericEnumConverter<E extends Enum<E> & EnumValue>
        implements AttributeConverter<E, Long> {

    private final Class<E> enumClass;

    public GenericEnumConverter(Class<E> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public Long convertToDatabaseColumn(E attribute) {

        return attribute == null
                ? null
                : attribute.getValue();
    }

    @Override
    public E convertToEntityAttribute(Long value) {

        if (value == null) {
            return null;
        }

        for (E enumValue : enumClass.getEnumConstants()) {

            if (enumValue.getValue().equals(value)) {
                return enumValue;
            }
        }

        throw new IllegalArgumentException(
                "Unknown value " + value
                + " for enum " + enumClass.getSimpleName()
        );
    }
}
