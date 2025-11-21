package com.astrapay.dto.res;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private String message;
    private T data;
}
