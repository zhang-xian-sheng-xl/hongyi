package com.hongyi.hr.module.hy.service.docfile;

import java.util.*;
import javax.validation.*;
import com.hongyi.hr.module.hy.controller.admin.docfile.vo.*;
import com.hongyi.hr.module.hy.dal.dataobject.docfile.DocFileDO;
import com.hongyi.hr.framework.common.pojo.PageResult;
import com.hongyi.hr.framework.common.pojo.PageParam;

/**
 * 文档分类 Service 接口
 *
 * @author zxl
 */
public interface DocFileService {

    /**
     * 创建文档分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDocFile(@Valid DocFileSaveReqVO createReqVO);

    /**
     * 更新文档分类
     *
     * @param updateReqVO 更新信息
     */
    void updateDocFile(@Valid DocFileSaveReqVO updateReqVO);

    /**
     * 删除文档分类
     *
     * @param id 编号
     */
    void deleteDocFile(Long id);


    /**
     * 获得文档分类
     *
     * @param id 编号
     * @return 文档分类
     */
    DocFileDO getDocFile(Long id);

    /**
     * 获得文档分类列表
     *
     * @param listReqVO 查询条件
     * @return 文档分类列表
     */
    List<DocFileDO> getDocFileList(DocFileListReqVO listReqVO);

    Integer getMaxno(Long parentId);
}
