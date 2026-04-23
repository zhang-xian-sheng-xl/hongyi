package com.hongyi.hr.module.hy.service.docfile;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.hongyi.hr.module.hy.controller.admin.docfile.vo.*;
import com.hongyi.hr.module.hy.dal.dataobject.docfile.DocFileDO;
import com.hongyi.hr.framework.common.pojo.PageResult;
import com.hongyi.hr.framework.common.pojo.PageParam;
import com.hongyi.hr.framework.common.util.object.BeanUtils;

import com.hongyi.hr.module.hy.dal.mysql.docfile.DocFileMapper;

import static com.hongyi.hr.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.hongyi.hr.framework.common.util.collection.CollectionUtils.convertList;
import static com.hongyi.hr.framework.common.util.collection.CollectionUtils.diffList;
import static com.hongyi.hr.module.hy.enums.ErrorCodeConstants.*;

/**
 * 文档分类 Service 实现类
 *
 * @author zxl
 */
@Service
@Validated
public class DocFileServiceImpl implements DocFileService {

    @Resource
    private DocFileMapper docFileMapper;

    @Override
    public Long createDocFile(DocFileSaveReqVO createReqVO) {
        // 校验上级的有效性
        validateParentDocFile(null, createReqVO.getParentId());
        // 校验标题的唯一性
        validateDocFileNameUnique(null, createReqVO.getParentId(), createReqVO.getName());

        // 插入
        DocFileDO docFile = BeanUtils.toBean(createReqVO, DocFileDO.class);
        docFileMapper.insert(docFile);

        // 返回
        return docFile.getId();
    }

    @Override
    public void updateDocFile(DocFileSaveReqVO updateReqVO) {
        // 校验存在
        validateDocFileExists(updateReqVO.getId());
        // 校验上级的有效性
        validateParentDocFile(updateReqVO.getId(), updateReqVO.getParentId());
        // 校验标题的唯一性
        validateDocFileNameUnique(updateReqVO.getId(), updateReqVO.getParentId(), updateReqVO.getName());

        // 更新
        DocFileDO updateObj = BeanUtils.toBean(updateReqVO, DocFileDO.class);
        docFileMapper.updateById(updateObj);
    }

    @Override
    public void deleteDocFile(Long id) {
        // 校验存在
        validateDocFileExists(id);
        // 校验是否有子文档分类
        if (docFileMapper.selectCountByParentId(id) > 0) {
            throw exception(DOC_FILE_EXITS_CHILDREN);
        }
        // 删除
        docFileMapper.deleteById(id);
    }


    private void validateDocFileExists(Long id) {
        if (docFileMapper.selectById(id) == null) {
            throw exception(DOC_FILE_NOT_EXISTS);
        }
    }

    private void validateParentDocFile(Long id, Long parentId) {
        if (parentId == null || DocFileDO.PARENT_ID_ROOT.equals(parentId)) {
            return;
        }
        // 1. 不能设置自己为父文档分类
        if (Objects.equals(id, parentId)) {
            throw exception(DOC_FILE_PARENT_ERROR);
        }
        // 2. 父文档分类不存在
        DocFileDO parentDocFile = docFileMapper.selectById(parentId);
        if (parentDocFile == null) {
            throw exception(DOC_FILE_PARENT_NOT_EXITS);
        }
        // 3. 递归校验父文档分类，如果父文档分类是自己的子文档分类，则报错，避免形成环路
        if (id == null) { // id 为空，说明新增，不需要考虑环路
            return;
        }
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            // 3.1 校验环路
            parentId = parentDocFile.getParentId();
            if (Objects.equals(id, parentId)) {
                throw exception(DOC_FILE_PARENT_IS_CHILD);
            }
            // 3.2 继续递归下一级父文档分类
            if (parentId == null || DocFileDO.PARENT_ID_ROOT.equals(parentId)) {
                break;
            }
            parentDocFile = docFileMapper.selectById(parentId);
            if (parentDocFile == null) {
                break;
            }
        }
    }

    private void validateDocFileNameUnique(Long id, Long parentId, String name) {
        DocFileDO docFile = docFileMapper.selectByParentIdAndName(parentId, name);
        if (docFile == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的文档分类
        if (id == null) {
            throw exception(DOC_FILE_NAME_DUPLICATE);
        }
        if (!Objects.equals(docFile.getId(), id)) {
            throw exception(DOC_FILE_NAME_DUPLICATE);
        }
    }

    @Override
    public DocFileDO getDocFile(Long id) {
        return docFileMapper.selectById(id);
    }

    @Override
    public List<DocFileDO> getDocFileList(DocFileListReqVO listReqVO) {
        return docFileMapper.selectList(listReqVO);
    }

    @Override
    public Integer getMaxno(Long parentId) {
        return docFileMapper.getMaxno(parentId);
    }

}
