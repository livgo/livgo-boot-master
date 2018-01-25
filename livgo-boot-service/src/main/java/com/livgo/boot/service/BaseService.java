package com.livgo.boot.service;

import com.livgo.boot.common.Const;
import org.apache.log4j.Logger;

/**
 * Description:Redis客户端
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class BaseService {

    public Logger logger = Logger.getLogger(getClass());

    /**
     * 获取分页的起始位置
     *
     * @param pageNum
     * @return
     */
    public static int getPageStartIndex(int pageNum) {
        return pageNum > 0 ? (pageNum - 1) * Const.PAGE_SIZE : 0;
    }

}
