package com.hongyi.hr.module.hy.controller.admin.docfile.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.hongyi.hr.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.hongyi.hr.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 文档分类列表 Request VO")
@Data
public class DocFileListReqVO {

    @Schema(description = "上级", example = "6135")
    private Long parentId;

    @Schema(description = "标题", example = "王五")
    private String name;

    @Schema(description = "排序")
    private Integer numSeq;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
