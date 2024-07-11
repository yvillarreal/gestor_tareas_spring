package com.app.config;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody<T> {
    public final T result;
    public final ApiError error;
    public final String msg;


    public ResponseBody(final String msg, final T result) {
            this.result = result;
            this.error = null;
            this.msg = msg;
    }

    public ResponseBody(final ApiError apiError,final String msg){
        this.result = null;
        this.error = apiError;
        this.msg = null;
    }

}
