package com.hongyi.hr.module.hy.enums;

import com.hongyi.hr.framework.common.exception.ErrorCode;

/**
 * Infra 错误码枚举类
 *
 * infra 系统，使用 1-001-000-000 段
 */
public interface ErrorCodeConstants {


    ErrorCode NOTE_NOT_EXISTS = new ErrorCode(1_001_600_001, "我的日记不存在");

}
