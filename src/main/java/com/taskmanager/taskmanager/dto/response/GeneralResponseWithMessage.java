package com.taskmanager.taskmanager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GeneralResponseWithMessage<T> {
    T data;
    String message;
    public static <T> GeneralResponseWithMessage<T> of(T data, String message) {
        return new GeneralResponseWithMessage<>(data, message);
    }
}
