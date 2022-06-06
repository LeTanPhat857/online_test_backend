package com.sunflower.onlinetest.rest.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDTO {

    private String token;

    private int timeToLive;
}
