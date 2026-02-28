package com.hongyi.hr.module.hy.service.note;

import java.util.*;
import javax.validation.*;
import com.hongyi.hr.module.hy.controller.admin.note.vo.*;
import com.hongyi.hr.module.hy.dal.dataobject.note.NoteDO;
import com.hongyi.hr.framework.common.pojo.PageResult;
import com.hongyi.hr.framework.common.pojo.PageParam;

/**
 * 我的日记 Service 接口
 *
 * @author zxl
 */
public interface NoteService {

    /**
     * 创建我的日记
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Integer createNote(@Valid NoteSaveReqVO createReqVO);

    /**
     * 更新我的日记
     *
     * @param updateReqVO 更新信息
     */
    void updateNote(@Valid NoteSaveReqVO updateReqVO);

    /**
     * 删除我的日记
     *
     * @param id 编号
     */
    void deleteNote(Integer id);

    /**
    * 批量删除我的日记
    *
    * @param ids 编号
    */
    void deleteNoteListByIds(List<Integer> ids);

    /**
     * 获得我的日记
     *
     * @param id 编号
     * @return 我的日记
     */
    NoteDO getNote(Integer id);

    /**
     * 获得我的日记分页
     *
     * @param pageReqVO 分页查询
     * @return 我的日记分页
     */
    PageResult<NoteDO> getNotePage(NotePageReqVO pageReqVO);

}