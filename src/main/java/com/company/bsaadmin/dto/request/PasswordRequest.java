package com.company.bsaadmin.dto.request;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
}