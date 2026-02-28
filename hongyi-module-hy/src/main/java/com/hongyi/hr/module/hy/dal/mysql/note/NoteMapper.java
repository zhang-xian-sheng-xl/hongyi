package com.hongyi.hr.module.hy.dal.mysql.note;

import java.util.*;

import com.hongyi.hr.framework.common.pojo.PageResult;
import com.hongyi.hr.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.hongyi.hr.framework.mybatis.core.mapper.BaseMapperX;
import com.hongyi.hr.module.hy.dal.dataobject.note.NoteDO;
import org.apache.ibatis.annotations.Mapper;
import com.hongyi.hr.module.hy.controller.admin.note.vo.*;

/**
 * 我的日记 Mapper
 *
 * @author zxl
 */
@Mapper
public interface NoteMapper extends BaseMapperX<NoteDO> {

    default PageResult<NoteDO> selectPage(NotePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NoteDO>()
                .likeIfPresent(NoteDO::getName, reqVO.getName())
                .eqIfPresent(NoteDO::getItem, reqVO.getItem())
                .eqIfPresent(NoteDO::getContent, reqVO.getContent())
                .eqIfPresent(NoteDO::getAnnexUrl, reqVO.getAnnexUrl())
                .betweenIfPresent(NoteDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(NoteDO::getId));
    }

}