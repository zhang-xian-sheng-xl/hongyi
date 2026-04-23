package com.hongyi.hr.module.hy.controller.admin.docfile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 文档分类新增/修改 Request VO")
@Data
public class DocFileSaveReqVO {

    @Schema(description = "主鍵id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26629")
    private Long id;

    @Schema(description = "上级", example = "6135")
    private Long parentId;

    @Schema(description = "标题", example = "王五")
    private String name;

    @Schema(description = "排序")
    private Integer numSeq;

}
