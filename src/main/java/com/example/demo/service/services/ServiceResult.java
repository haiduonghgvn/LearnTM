package com.example.demo.service.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResult {
    private Status status = Status.SUCCESS;
    private String message;
    private Object data;
    public enum Status {
        SUCCESS, FAILED;
    }

}
