package com.company.bsaadmin.util.converter;

import javax.persistence.Converter;

import com.company.bsaadmin.util.ProjectEnum;

@Converter
public class AttendanceStatusConverter
        extends GenericEnumConverter<ProjectEnum.AttendanceStatus> {

    public AttendanceStatusConverter() {
        super(ProjectEnum.AttendanceStatus.class);
    }
}