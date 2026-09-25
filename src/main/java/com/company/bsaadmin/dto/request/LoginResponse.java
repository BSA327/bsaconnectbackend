package com.company.bsaadmin.dto.request;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class LoginResponse {
    private String token;
    private UserInfo user;

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    public static class UserInfo {
        private Long userId;
        private String role;
        private String loginName;
    }
}