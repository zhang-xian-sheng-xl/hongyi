package com.hongyi.hr.module.hy.controller.admin.note;

import com.hongyi.hr.framework.common.pojo.CommonResult;
import com.hongyi.hr.module.hy.service.sppt.SpptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.hongyi.hr.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 审批平台")
@RestController
@RequestMapping("/hy/sppt")
@Validated
public class SpptController {

    @Resource
    private SpptService spptService;






    @GetMapping("/get")
    @Operation(summary = "获得我的日记")
    @Parameter(name = "pwd", description = "编号", required = true, example = "1024")
    public CommonResult<String> jiemi(@RequestParam("pwd") String pwd) {
        String  result = spptService.jiemi(pwd);
        return success(result);
    }



}
