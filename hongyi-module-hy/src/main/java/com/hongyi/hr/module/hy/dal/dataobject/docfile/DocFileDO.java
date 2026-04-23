package com.hongyi.hr.module.hy.dal.dataobject.docfile;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.hongyi.hr.framework.mybatis.core.dataobject.BaseDO;

/**
 * 文档分类 DO
 *
 * @author zxl
 */
@TableName("hy_doc_file")
@KeySequence("hy_doc_file_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocFileDO extends BaseDO {

    public static final Long PARENT_ID_ROOT = 0L;

    /**
     * 主鍵id
     */
    @TableId
    private Long id;
    /**
     * 上级
     */
    private Long parentId;
    /**
     * 标题
     */
    private String name;
    /**
     * 排序
     */
    private Integer numSeq;


}
