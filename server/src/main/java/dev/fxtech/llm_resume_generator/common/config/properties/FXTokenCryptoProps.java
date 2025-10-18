package dev.fxtech.llm_resume_generator.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "token-crypto")
public class FXTokenCryptoProps {

    private String activeVersion;
    private Map<String, String > tokenKeys;

}
