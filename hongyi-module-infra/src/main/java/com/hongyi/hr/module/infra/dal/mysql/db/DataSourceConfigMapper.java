package com.hongyi.hr.module.infra.dal.mysql.db;

import com.hongyi.hr.framework.mybatis.core.mapper.BaseMapperX;
import com.hongyi.hr.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
