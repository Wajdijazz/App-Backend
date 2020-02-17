package com.followup.davidson.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus errCode;
    private String errMsg;

    public EntityNotFoundException(HttpStatus errCode, String errMsg) {
        super();
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
