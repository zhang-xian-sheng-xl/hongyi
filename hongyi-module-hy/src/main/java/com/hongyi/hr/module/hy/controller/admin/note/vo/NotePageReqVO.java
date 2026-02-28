package com.hongyi.hr.module.hy.controller.admin.note.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.hongyi.hr.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.hongyi.hr.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 我的日记分页 Request VO")
@Data
public class NotePageReqVO extends PageParam {

    @Schema(description = "标题", example = "王五")
    private String name;

    @Schema(description = "系统")
    private String item;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "附件", example = "https://www.iocoder.cn")
    private String annexUrl;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}