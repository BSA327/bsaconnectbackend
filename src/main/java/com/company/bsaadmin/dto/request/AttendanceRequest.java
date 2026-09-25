package com.company.bsaadmin.dto.request;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AttendanceRequest {
    private Double latitude;
    private Double longitude;
    private String location;
    private String ip;
}