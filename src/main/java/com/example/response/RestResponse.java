package com.example.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    private T data;
    private String message;
    private int resultCode;
}
