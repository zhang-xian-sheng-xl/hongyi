package com.hongyi.hr.module.hy.controller.admin.note.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;
import com.hongyi.hr.framework.excel.core.annotations.DictFormat;
import com.hongyi.hr.framework.excel.core.convert.DictConvert;

@Schema(description = "管理后台 - 我的日记 Response VO")
@Data
@ExcelIgnoreUnannotated
public class NoteRespVO {

    @Schema(description = "主鍵id", requiredMode = Schema.RequiredMode.REQUIRED, example = "28549")
    @ExcelProperty("主鍵id")
    private Integer id;

    @Schema(description = "标题", example = "王五")
    @ExcelProperty("标题")
    private String name;

    @Schema(description = "系统")
    @ExcelProperty(value = "系统", converter = DictConvert.class)
    @DictFormat("project_list") // TODO 代码优化：建议设置到对应的 DictTypeConstants 枚举类中
    private String item;

    @Schema(description = "内容")
    @ExcelProperty("内容")
    private String content;

    @Schema(description = "附件", example = "https://www.iocoder.cn")
    @ExcelProperty("附件")
    private String annexUrl;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}