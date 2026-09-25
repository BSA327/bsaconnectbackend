package com.company.bsaadmin.dto.request;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class SiteVisitRequest {
    private Long inventoryId;
    private Long enquiryId;
    private Long bdmId;
    private LocalDate date;
    private String remarks;
}