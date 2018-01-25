package com.livgo.boot.data.mapper;

import com.livgo.boot.model.entity.Demo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public interface DemoMapper {

    List<Demo> selectByPage(Map<String, Object> param);

}