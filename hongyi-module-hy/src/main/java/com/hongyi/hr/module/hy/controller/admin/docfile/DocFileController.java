package com.hongyi.hr.module.hy.controller.admin.docfile;

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

import com.hongyi.hr.module.hy.controller.admin.docfile.vo.*;
import com.hongyi.hr.module.hy.dal.dataobject.docfile.DocFileDO;
import com.hongyi.hr.module.hy.service.docfile.DocFileService;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

@Tag(name = "管理后台 - 文档分类")
@RestController
@RequestMapping("/hy/doc-file")
@Validated
public class DocFileController {

    @Resource
    private DocFileService docFileService;

    @PostMapping("/create")
    @Operation(summary = "创建文档分类")
    @PreAuthorize("@ss.hasPermission('hy:doc-file:create')")
    public CommonResult<Long> createDocFile(@Valid @RequestBody DocFileSaveReqVO createReqVO) {
        return success(docFileService.createDocFile(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新文档分类")
    @PreAuthorize("@ss.hasPermission('hy:doc-file:update')")
    public CommonResult<Boolean> updateDocFile(@Valid @RequestBody DocFileSaveReqVO updateReqVO) {
        docFileService.updateDocFile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文档分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('hy:doc-file:delete')")
    public CommonResult<Boolean> deleteDocFile(@RequestParam("id") Long id) {
        docFileService.deleteDocFile(id);
        return success(true);
    }


    @GetMapping("/get")
    @Operation(summary = "获得文档分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hy:doc-file:query')")
    public CommonResult<DocFileRespVO> getDocFile(@RequestParam("id") Long id) {
        DocFileDO docFile = docFileService.getDocFile(id);
        return success(BeanUtils.toBean(docFile, DocFileRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得文档分类列表")
    @PreAuthorize("@ss.hasPermission('hy:doc-file:query')")
    public CommonResult<List<DocFileRespVO>> getDocFileList(@Valid DocFileListReqVO listReqVO) {
        List<DocFileDO> list = docFileService.getDocFileList(listReqVO);
        return success(BeanUtils.toBean(list, DocFileRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文档分类 Excel")
    @PreAuthorize("@ss.hasPermission('hy:doc-file:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDocFileExcel(@Valid DocFileListReqVO listReqVO,
              HttpServletResponse response) throws IOException {
        List<DocFileDO> list = docFileService.getDocFileList(listReqVO);
        // 导出 Excel
        ExcelUtils.write(response, "文档分类.xls", "数据", DocFileRespVO.class,
                        BeanUtils.toBean(list, DocFileRespVO.class));
    }
    @GetMapping("/getMaxno")
    @Operation(summary = "获得文档分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('hy:doc-file:query')")
    public CommonResult<Integer> getMaxno (@RequestParam("parentId") Long parentId) {
        Integer maxseq = docFileService.getMaxno(parentId);
        return success(maxseq == null ? 1 : maxseq + 1);
    }

}
