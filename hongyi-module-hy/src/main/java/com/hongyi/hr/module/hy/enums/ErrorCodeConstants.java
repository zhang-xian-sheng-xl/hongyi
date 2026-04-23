package com.hongyi.hr.module.hy.enums;

import com.hongyi.hr.framework.common.exception.ErrorCode;

/**
 * Infra 错误码枚举类
 *
 * infra 系统，使用 1-001-000-000 段
 */
public interface ErrorCodeConstants {


    ErrorCode NOTE_NOT_EXISTS = new ErrorCode(1_001_600_001, "我的日记不存在");
    ErrorCode DOC_FILE_NOT_EXISTS = new ErrorCode(1_001_600_002, "文档分类不存在");
    ErrorCode DOC_FILE_EXITS_CHILDREN = new ErrorCode(1_001_600_003, "存在存在子文档分类，无法删除");
    ErrorCode DOC_FILE_PARENT_NOT_EXITS = new ErrorCode(1_001_600_004,"父级文档分类不存在");
    ErrorCode DOC_FILE_PARENT_ERROR = new ErrorCode(1_001_600_005, "不能设置自己为父文档分类");
    ErrorCode DOC_FILE_NAME_DUPLICATE = new ErrorCode(1_001_600_006, "已经存在该标题的文档分类");
    ErrorCode DOC_FILE_PARENT_IS_CHILD = new ErrorCode(1_001_600_007, "不能设置自己的子DocFile为父DocFile");


}
