package dev.fxtech.llm_resume_generator.llm.model.eo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import dev.fxtech.llm_resume_generator.common.model.FXBaseEO;
import dev.fxtech.llm_resume_generator.common.security.FXTokenCryptoUtil.FXEncryptedToken;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@TableName("fx_user_tokens")
public class FXUserTokenEO extends FXBaseEO {

    @Schema(description = "Provider of the model")
    @TableField("model_provider")
    private String modelProvider;

    @Schema(description = "Family of the Model")
    @TableField("model_family")
    private String modelFamily;

    @Schema(description = "URL of the Model")
    @TableField("request_url")
    private String requestUrl;

    @Schema(description = "Encrypted Model Access Token")
    @TableField("model_token_enc")
    private FXEncryptedToken modelTokenEnc;

    @Schema(description = "Last 4 Digit of the Access Token")
    @TableField("model_token_last4")
    private String modelTokenLast4;

}
