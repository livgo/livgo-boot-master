package com.livgo.boot.api.service;

import com.livgo.boot.api.mapper.DemoMapper;
import com.livgo.boot.model.entity.Demo;
import com.livgo.boot.service.DemoBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:Demo业务Service
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Service
@Transactional
public class DemoService extends DemoBaseService {

    @Autowired
    private DemoMapper demoMapper;

    /**
     * 分页查询
     *
     * @param pageNum
     * @return
     */
    public List<Demo> list1Demo(int pageNum) {
        return super.listDemo(pageNum);
    }

    /**
     * 分页查询
     *
     * @return
     */
    public int list2Demo() {
        return demoMapper.selectCount(null);
    }


}
