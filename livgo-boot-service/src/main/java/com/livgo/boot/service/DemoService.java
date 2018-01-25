package com.livgo.boot.service;

import com.livgo.boot.common.Const;
import com.livgo.boot.data.mapper.DemoMapper;
import com.livgo.boot.model.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Service
@Transactional
public class DemoService extends BaseService {

    @Autowired
    private DemoMapper demoMapper;

    /**
     * 分页查询
     *
     * @param pageNum
     * @return
     */
    public List<Demo> listDemo(int pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("limit", getPageStartIndex(pageNum) + "," + Const.PAGE_SIZE);
        return demoMapper.selectByPage(map);
    }

}
