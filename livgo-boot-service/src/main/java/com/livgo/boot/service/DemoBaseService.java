package com.livgo.boot.service;

import com.livgo.boot.common.Const;
import com.livgo.boot.data.mapper.DemoBaseMapper;
import com.livgo.boot.model.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:Demo业务基础Service
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Service
@Transactional
public class DemoBaseService extends BaseService {

    @Autowired
    private DemoBaseMapper demoBaseMapper;

    /**
     * 分页查询
     *
     * @param pageNum
     * @return
     */
    public List<Demo> listDemo(int pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("limit", getPageStartIndex(pageNum) + "," + Const.PAGE_SIZE);
        return demoBaseMapper.selectByPage(map);
    }

}
