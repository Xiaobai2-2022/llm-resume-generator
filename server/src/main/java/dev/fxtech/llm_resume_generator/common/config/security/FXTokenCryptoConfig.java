package dev.fxtech.llm_resume_generator.common.config.security;

import dev.fxtech.llm_resume_generator.common.config.properties.FXTokenCryptoProps;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FXTokenCryptoConfig {

    @Bean
    public Map<String, byte[]> tokenKeys(FXTokenCryptoProps props) {
        Map<String, byte[]> keys = new HashMap<>();
        props.getTokenKeys()
                .forEach(
                        (v, k)->
                        keys.put(v, Base64.getDecoder().decode(k)));
        return Collections.unmodifiableMap(keys);
    }

    @Bean
    public String activeKeyVersion(FXTokenCryptoProps props) {
        return props.getActiveVersion();
    }

}
