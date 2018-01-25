package com.livgo.boot.api.service;

import com.livgo.boot.data.mapper.DemoMapper;
import com.livgo.boot.model.entity.Demo;
import com.livgo.boot.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Service
@Transactional
public class DemoApiService extends DemoService {

    @Autowired
    private DemoMapper demoMapper;

    /**
     * 分页查询
     *
     * @param pageNum
     * @return
     */
    public List<Demo> listDemo(int pageNum) {
        return super.listDemo(pageNum);
    }

}
