package com.hongyi.hr.module.hy.dal.mysql.docfile;

import java.util.*;

import com.hongyi.hr.framework.common.pojo.PageResult;
import com.hongyi.hr.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.hongyi.hr.framework.mybatis.core.mapper.BaseMapperX;
import com.hongyi.hr.module.hy.dal.dataobject.docfile.DocFileDO;
import org.apache.ibatis.annotations.Mapper;
import com.hongyi.hr.module.hy.controller.admin.docfile.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 文档分类 Mapper
 *
 * @author zxl
 */
@Mapper
public interface DocFileMapper extends BaseMapperX<DocFileDO> {

    default List<DocFileDO> selectList(DocFileListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DocFileDO>()
                .eqIfPresent(DocFileDO::getParentId, reqVO.getParentId())
                .likeIfPresent(DocFileDO::getName, reqVO.getName())
                .eqIfPresent(DocFileDO::getNumSeq, reqVO.getNumSeq())
                .betweenIfPresent(DocFileDO::getCreateTime, reqVO.getCreateTime())
                .orderByAsc(DocFileDO::getNumSeq));
    }

    default DocFileDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(DocFileDO::getParentId, parentId, DocFileDO::getName, name);
    }

    default Long selectCountByParentId(Long parentId) {
        return selectCount(DocFileDO::getParentId, parentId);
    }

    @Select("select max(num_seq) from hy_doc_file where parent_id = #{parentId}")
    Integer getMaxno(@Param("parentId") Long parentId);
}
