package dev.fxtech.llm_resume_generator.llm.model.dto;

import dev.fxtech.llm_resume_generator.common.exception.FXErrno;
import dev.fxtech.llm_resume_generator.common.exception.FXException;
import dev.fxtech.llm_resume_generator.common.security.FXTokenCryptoUtil;
import org.springframework.util.StringUtils;

public record FXDecryptTokenDTO(
    String id,
    String createdBy,
    FXTokenCryptoUtil.FXEncryptedToken encryptedToken
) {
    public FXDecryptTokenDTO {
        if(!StringUtils.hasText(id)) {
            throw new FXException(FXErrno.USERID_MISSING, "ID does not exist in FXDecryptTokenDTO");
        }
        if(!StringUtils.hasText(createdBy)) {
            throw new FXException(FXErrno.USERNAME_MISSING, "Name does not exist in FXDecryptTokenDTO");
        }
        if(encryptedToken == null) {
            throw new FXException(FXErrno.TOKEN_MISSING, "Token does not exist in FXDecryptTokenDTO");
        }
    }
}
