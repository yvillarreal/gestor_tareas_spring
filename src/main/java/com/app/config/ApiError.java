package com.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class ApiError {
    private final String descripcion;
    private final HttpStatus status;

    public ApiError(String descripcion, HttpStatus status) {
        this.descripcion = descripcion;
        this.status = status;
    }
}
