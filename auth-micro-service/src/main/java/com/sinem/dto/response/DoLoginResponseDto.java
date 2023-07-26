package com.sinem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoLoginResponseDto {
    /**
     * 200 - Basarili
     * 400 - Hatali istek
     */
    Integer status;
    String result;
    String token;   // JWT token
}
