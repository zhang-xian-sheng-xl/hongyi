package com.hongyi.hr.module.hy.controller.admin.docfile.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 文档分类 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DocFileRespVO {

    @Schema(description = "主鍵id", requiredMode = Schema.RequiredMode.REQUIRED, example = "26629")
    @ExcelProperty("主鍵id")
    private Long id;

    @Schema(description = "上级", example = "6135")
    @ExcelProperty("上级")
    private Long parentId;

    @Schema(description = "标题", example = "王五")
    @ExcelProperty("标题")
    private String name;

    @Schema(description = "排序")
    @ExcelProperty("排序")
    private Integer numSeq;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
