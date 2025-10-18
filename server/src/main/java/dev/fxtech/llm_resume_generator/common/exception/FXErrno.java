package dev.fxtech.llm_resume_generator.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FXErrno {

    RESOURCE_MISSING(4040000, "Requested Resource is Missing", HttpStatus.NOT_FOUND),
    USERID_MISSING(4040001, "Requested User ID is Missing", HttpStatus.NOT_FOUND),
    USERNAME_MISSING(4040002, "Requested User Name is Missing", HttpStatus.NOT_FOUND),
    TOKEN_MISSING(4040002, "Requested Token is Missing", HttpStatus.NOT_FOUND),

    ;

    private final int errno;
    private final String msg;
    private final HttpStatus status;

    FXErrno(int errno, String msg) {
        this.errno = errno;
        this.msg = msg;
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    FXErrno(int errno, String msg, HttpStatus status) {
        this.errno = errno;
        this.msg = msg;
        this.status = status;
    }

}
