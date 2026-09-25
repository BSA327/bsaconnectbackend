package com.company.bsaadmin.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class ErrorDto {

	private String message;
}
