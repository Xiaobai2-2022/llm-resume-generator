package dev.fxtech.llm_resume_generator.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum FXErrno {

    RESOURCE_MISSING(4040000, "Requested Resource is Missing", HttpStatus.NOT_FOUND),
    USERID_MISSING(4040001, "Requested User ID is Missing", HttpStatus.NOT_FOUND),
    USERNAME_MISSING(4040002, "Requested User Name is Missing", HttpStatus.NOT_FOUND),
    TOKEN_MISSING(4040002, "Requested Token is Missing", HttpStatus.NOT_FOUND),


    SERVER_ERROR(5000000, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    KEY_VERSION_NOT_FOUND(5000001, "Key Version does not Exist", HttpStatus.INTERNAL_SERVER_ERROR),
    ENCRYPT_ERROR(5000002, "Encrypt Error", HttpStatus.INTERNAL_SERVER_ERROR),
    DECRYPT_ERROR(5000003, "Decrypt Error", HttpStatus.INTERNAL_SERVER_ERROR);

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
