package com.livgo.boot.api.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Description:Demo业务Mapper
 * Author:     gaocl
 * Date:       2018/1/30
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Repository
public interface DemoMapper {

    int selectCount(Map<String, Object> param);

}
