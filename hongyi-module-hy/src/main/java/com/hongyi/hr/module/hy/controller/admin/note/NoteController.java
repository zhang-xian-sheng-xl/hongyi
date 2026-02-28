package com.hongyi.hr.module.hy.controller.admin.note;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.hongyi.hr.framework.common.pojo.PageParam;
import com.hongyi.hr.framework.common.pojo.PageResult;
import com.hongyi.hr.framework.common.pojo.CommonResult;
import com.hongyi.hr.framework.common.util.object.BeanUtils;
import static com.hongyi.hr.framework.common.pojo.CommonResult.success;

import com.hongyi.hr.framework.excel.core.util.ExcelUtils;

import com.hongyi.hr.framework.apilog.core.annotation.ApiAccessLog;
import static com.hongyi.hr.framework.apilog.core.enums.OperateTypeEnum.*;

import com.hongyi.hr.module.hy.controller.admin.note.vo.*;
import com.hongyi.hr.module.hy.dal.dataobject.note.NoteDO;
import com.hongyi.hr.module.hy.service.note.NoteService;

@Tag(name = "管理后台 - 我的日记")
@RestController
@RequestMapping("/hy/note")
@Validated
public class NoteController {

    @Resource
    private NoteService noteService;

    @PostMapping("/create")
    @Operation(summary = "创建我的日记")
    @PreAuthorize("@ss.hasPermission('hy:note:create')")
    public CommonResult<Integer> createNote(@Valid @RequestBody NoteSaveReqVO createReqVO) {
        return success(noteService.createNote(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新我的日记")
    @PreAuthorize("@ss.hasPermission('hy:note:update')")
    public CommonResult<Boolean> updateNote(@Valid @RequestBody NoteSaveReqVO updateReqVO) {
        noteService.updateNote(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除我的日记")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hy:note:delete')")
    public CommonResult<Boolean> deleteNote(@RequestParam("id") Integer id) {
        noteService.deleteNote(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除我的日记")
                @PreAuthorize("@ss.hasPermission('hy:note:delete')")
    public CommonResult<Boolean> deleteNoteList(@RequestParam("ids") List<Integer> ids) {
        noteService.deleteNoteListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得我的日记")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hy:note:query')")
    public CommonResult<NoteRespVO> getNote(@RequestParam("id") Integer id) {
        NoteDO note = noteService.getNote(id);
        return success(BeanUtils.toBean(note, NoteRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得我的日记分页")
    @PreAuthorize("@ss.hasPermission('hy:note:query')")
    public CommonResult<PageResult<NoteRespVO>> getNotePage(@Valid NotePageReqVO pageReqVO) {
        PageResult<NoteDO> pageResult = noteService.getNotePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, NoteRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出我的日记 Excel")
    @PreAuthorize("@ss.hasPermission('hy:note:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportNoteExcel(@Valid NotePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<NoteDO> list = noteService.getNotePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "我的日记.xls", "数据", NoteRespVO.class,
                        BeanUtils.toBean(list, NoteRespVO.class));
    }

}