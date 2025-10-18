package dev.fxtech.llm_resume_generator.llm.model.dto;

import dev.fxtech.llm_resume_generator.common.exception.*;

import org.springframework.util.StringUtils;

public record FXEncryptTokenDTO(
        String id,
        String createdBy,
        String rawToken
) {
    public FXEncryptTokenDTO {
        if(!StringUtils.hasText(id)) {
            throw new FXException(FXErrno.USERID_MISSING, "ID does not exist in FXEncryptTokenDTO");
        }
        if(!StringUtils.hasText(createdBy)) {
            throw new FXException(FXErrno.USERNAME_MISSING, "Name does not exist in FXEncryptTokenDTO");
        }
        if(!StringUtils.hasText(rawToken)) {
            throw new FXException(FXErrno.TOKEN_MISSING, "Token does not exist in FXEncryptTokenDTO");
        }
    }
}
