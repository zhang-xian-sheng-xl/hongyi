package com.hongyi.hr.module.hy.dal.dataobject.note;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.hongyi.hr.framework.mybatis.core.dataobject.BaseDO;

/**
 * 我的日记 DO
 *
 * @author zxl
 */
@TableName("hy_note")
@KeySequence("hy_note_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDO extends BaseDO {

    /**
     * 主鍵id
     */
    @TableId
    private Integer id;
    /**
     * 标题
     */
    private String name;
    /**
     * 系统
     *
     * 枚举 {@link TODO project_list 对应的类}
     */
    private String item;
    /**
     * 内容
     */
    private String content;
    /**
     * 附件
     */
    private String annexUrl;


}