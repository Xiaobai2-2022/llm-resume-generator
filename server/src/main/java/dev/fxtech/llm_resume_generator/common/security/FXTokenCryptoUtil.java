package dev.fxtech.llm_resume_generator.common.security;

import dev.fxtech.llm_resume_generator.common.exception.*;
import dev.fxtech.llm_resume_generator.llm.model.dto.FXDecryptTokenDTO;
import dev.fxtech.llm_resume_generator.llm.model.dto.FXEncryptTokenDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class FXTokenCryptoUtil {

    private final Map<String, byte[]> tokenKeys;
    private final String activeKeyVersion;

    public record FXEncryptedToken(String encryptedToken, String activeKeyVersion) {}

    public FXEncryptedToken generateFXEncryptedToken(FXEncryptTokenDTO dto) {

        byte[] key = ensureKey(this.activeKeyVersion, "encryption");
        byte[] aad = FXAESGCMUtil.buildAAD(dto.id(), dto.createdBy());
        String encryptedToken = FXAESGCMUtil.encrypt(dto.rawToken(), key, aad);

        return new FXEncryptedToken(encryptedToken, activeKeyVersion);

    }

    public String decryptFXEncryptedToken(FXDecryptTokenDTO dto) {

        FXEncryptedToken encryptedToken = dto.encryptedToken();

        byte[] key = ensureKey(encryptedToken.activeKeyVersion(), "decryption");
        byte[] aad = FXAESGCMUtil.buildAAD(dto.id(), dto.createdBy());

        return FXAESGCMUtil.decrypt(encryptedToken.encryptedToken(), key, aad);

    }

    private byte[] ensureKey(String activeKeyVersion, String msg) {
        byte[] key = tokenKeys.get(activeKeyVersion);
        if (key == null) {
            throw new FXException(FXErrno.KEY_VERSION_NOT_FOUND, "Active Key Version is not valid during " + msg + ".");
        }
        return key;
    }

}
