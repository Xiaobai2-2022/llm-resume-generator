package dev.fxtech.llm_resume_generator.common.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class FXBCryptUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

}
