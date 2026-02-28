package com.hongyi.hr.module.hy.service.note;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.hongyi.hr.module.hy.controller.admin.note.vo.*;
import com.hongyi.hr.module.hy.dal.dataobject.note.NoteDO;
import com.hongyi.hr.framework.common.pojo.PageResult;
import com.hongyi.hr.framework.common.pojo.PageParam;
import com.hongyi.hr.framework.common.util.object.BeanUtils;

import com.hongyi.hr.module.hy.dal.mysql.note.NoteMapper;

import static com.hongyi.hr.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.hongyi.hr.framework.common.util.collection.CollectionUtils.convertList;
import static com.hongyi.hr.framework.common.util.collection.CollectionUtils.diffList;
import static com.hongyi.hr.module.hy.enums.ErrorCodeConstants.*;

/**
 * 我的日记 Service 实现类
 *
 * @author zxl
 */
@Service
@Validated
public class NoteServiceImpl implements NoteService {

    @Resource
    private NoteMapper noteMapper;

    @Override
    public Integer createNote(NoteSaveReqVO createReqVO) {
        // 插入
        NoteDO note = BeanUtils.toBean(createReqVO, NoteDO.class);
        noteMapper.insert(note);

        // 返回
        return note.getId();
    }

    @Override
    public void updateNote(NoteSaveReqVO updateReqVO) {
        // 校验存在
        validateNoteExists(updateReqVO.getId());
        // 更新
        NoteDO updateObj = BeanUtils.toBean(updateReqVO, NoteDO.class);
        noteMapper.updateById(updateObj);
    }

    @Override
    public void deleteNote(Integer id) {
        // 校验存在
        validateNoteExists(id);
        // 删除
        noteMapper.deleteById(id);
    }

    @Override
        public void deleteNoteListByIds(List<Integer> ids) {
        // 删除
        noteMapper.deleteByIds(ids);
        }


    private void validateNoteExists(Integer id) {
        if (noteMapper.selectById(id) == null) {
            throw exception(NOTE_NOT_EXISTS);
        }
    }

    @Override
    public NoteDO getNote(Integer id) {
        return noteMapper.selectById(id);
    }

    @Override
    public PageResult<NoteDO> getNotePage(NotePageReqVO pageReqVO) {
        return noteMapper.selectPage(pageReqVO);
    }

}