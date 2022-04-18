package com.tomsproject.secret_santa.exception;

import lombok.Builder;

@Builder
public class ExceptionMessageDto {

    int errorId;
    String errorName;
    String methodMessage;
    String errorMessage;

}
