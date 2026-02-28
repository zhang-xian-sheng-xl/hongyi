package com.hongyi.hr.module.hy.controller.admin.note.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 我的日记新增/修改 Request VO")
@Data
public class NoteSaveReqVO {

    @Schema(description = "主鍵id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28549")
    private Integer id;

    @Schema(description = "标题", example = "王五")
    private String name;

    @Schema(description = "系统")
    private String item;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "附件", example = "https://www.iocoder.cn")
    private String annexUrl;

}