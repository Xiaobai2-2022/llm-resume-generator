package dev.fxtech.llm_resume_generator.common.exception;

import lombok.Getter;

@Getter
public class FXException extends RuntimeException {

    private final FXErrno errno;

    public FXException(FXErrno errno) {
        super(errno.getMsg());
        this.errno = errno;
    }

    public FXException(FXErrno errno, String msg) {
        super(msg);
        this.errno = errno;
    }

    public FXException(FXErrno errno, String msg, Throwable cause) {
        super(msg, cause);
        this.errno = errno;
    }

}
