package dev.fxtech.llm_resume_generator.common.security;

import dev.fxtech.llm_resume_generator.common.exception.FXErrno;
import dev.fxtech.llm_resume_generator.common.exception.FXException;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public final class FXAESGCMUtil {

    private FXAESGCMUtil() {}

    private static final int IV_LENGTH = 16;
    private static final int TAG_BITS = 128;
    private static final SecureRandom RNG = new SecureRandom();

    public static byte[] buildAAD(String id, String createdBy) {
        return (id + "|" + createdBy).getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    // Encrypts UTF-8 plaintext with AES-GCM, returns Base64(iv || ciphertext+tag).
    public static String encrypt(String plainValue, byte[] key, byte[] aad) {
        try {

            byte[] iv = new byte[IV_LENGTH];
            RNG.nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKey secretKey = new SecretKeySpec(key, "AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new GCMParameterSpec(TAG_BITS, iv));

            if(aad != null) {
                cipher.updateAAD(aad);
            }

            byte[] ct = cipher.doFinal(plainValue.getBytes(StandardCharsets.UTF_8));

            byte[] ivct = new byte[iv.length + ct.length];
            System.arraycopy(iv, 0, ivct, 0, iv.length);
            System.arraycopy(ct, 0, ivct, iv.length, ct.length);
            return Base64.getEncoder().encodeToString(ivct);

        } catch (NoSuchAlgorithmException nsae) {
            throw new FXException(FXErrno.ENCRYPT_ERROR, "Encrypt Algorithm Not Supported in AES/GCM.", nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new FXException(FXErrno.ENCRYPT_ERROR, "Encrypt Padding Not Supported in AES/GCM.", nspe);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new FXException(FXErrno.ENCRYPT_ERROR, "Encrypt Algorithm Invalid in AES/GCM.", iape);
        } catch (InvalidKeyException ike) {
            throw new FXException(FXErrno.ENCRYPT_ERROR, "Encrypt Key Not Supported in AES/GCM.", ike);
        } catch (IllegalBlockSizeException ibse) {
            throw new FXException(FXErrno.ENCRYPT_ERROR, "Encrypt Block Size Invalid in AES/GCM.", ibse);
        } catch (BadPaddingException bpe) {
            throw new FXException(FXErrno.ENCRYPT_ERROR, "Encrypt Padding Invalid in AES/GCM.", bpe);
        }

    }

    // Decrypts a Base64(iv || ciphertext+tag) string with AES-GCM and optional AAD.
    public static String decrypt(String encryptedValue, byte[] key, byte[] aad) {

        try {

            byte[] ivct = Base64.getDecoder().decode(encryptedValue);
            byte[] iv = java.util.Arrays.copyOfRange(ivct, 0, IV_LENGTH);
            byte[] ct = java.util.Arrays.copyOfRange(ivct, IV_LENGTH, ivct.length);


            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            SecretKey sk = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sk, new GCMParameterSpec(TAG_BITS, iv));
            if (aad != null) {
                cipher.updateAAD(aad);
            }

            byte[] pt = cipher.doFinal(ct);
            return new String(pt, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException nsae) {
            throw new FXException(FXErrno.DECRYPT_ERROR, "Decrypt Algorithm Not Supported in AES/GCM.", nsae);
        } catch (NoSuchPaddingException nspe) {
            throw new FXException(FXErrno.DECRYPT_ERROR, "Decrypt Padding Not Supported in AES/GCM.", nspe);
        } catch (InvalidAlgorithmParameterException iape) {
            throw new FXException(FXErrno.DECRYPT_ERROR, "Decrypt Algorithm Invalid in AES/GCM.", iape);
        } catch (InvalidKeyException ike) {
            throw new FXException(FXErrno.DECRYPT_ERROR, "Decrypt Key Not Supported in AES/GCM.", ike);
        } catch (IllegalBlockSizeException ibse) {
            throw new FXException(FXErrno.DECRYPT_ERROR, "Decrypt Block Size Invalid in AES/GCM.", ibse);
        } catch (BadPaddingException bpe) {
            throw new FXException(FXErrno.DECRYPT_ERROR, "Decrypt Padding Invalid in AES/GCM.", bpe);
        }

    }

}
