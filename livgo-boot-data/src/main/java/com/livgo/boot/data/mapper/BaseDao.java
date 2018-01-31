package com.livgo.boot.data.mapper;

import java.util.List;
import java.util.Map;

/**
 * Description: BaseDao
 * Author:     gaocl
 * Date:       2018/1/30
 * Version:    V1.0.0
 * Update:     更新说明
 */
@SuppressWarnings("rawtypes")
public interface BaseDao<T> {

    List<T> selectByPage(Map<String, Object> param);

}
