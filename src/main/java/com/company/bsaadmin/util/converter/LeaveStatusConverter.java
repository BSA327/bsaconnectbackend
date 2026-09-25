package com.company.bsaadmin.util.converter;

import javax.persistence.Converter;

import com.company.bsaadmin.util.ProjectEnum;

@Converter
public class LeaveStatusConverter
        extends GenericEnumConverter<ProjectEnum.LeaveStatus> {

    public LeaveStatusConverter() {
        super(ProjectEnum.LeaveStatus.class);
    }
}
