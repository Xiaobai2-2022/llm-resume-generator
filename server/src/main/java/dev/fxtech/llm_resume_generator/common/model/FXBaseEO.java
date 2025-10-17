package dev.fxtech.llm_resume_generator.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(description = "FX Base Entity Object")
public abstract class FXBaseEO {

    @Schema(description = "Primary Key ID")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @EqualsAndHashCode.Include
    protected String id;

    @Schema(description = "Created Timestamp")
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    protected LocalDateTime createdTime;

    @Schema(description = "Created User")
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    protected String createdBy;

    @Schema(description = "Last Updated Timestamp")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updatedTime;

    @Schema(description = "Update User")
    @TableField(value = "updated_by", fill = FieldFill.INSERT_UPDATE)
    protected String updatedBy;

}
