package com.sunflower.onlinetest.rest.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResponseObject {
    private String message;
    private Object data;
}
