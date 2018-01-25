package com.livgo.boot.api.web;

import com.livgo.boot.common.exception.BasicException;
import com.livgo.boot.common.model.bean.ResponseBean;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description:
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class BaseController {

    public Logger logger = Logger.getLogger(getClass());

    /**
     * 异常页面
     *
     * @param runtimeException
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView runtimeExceptionHandler(RuntimeException runtimeException) {
        runtimeException.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("error", ResponseBean.FAIL());
        return mav;
    }

    /**
     * ajax异步异常响应信息
     * throw new BasicException(getChildrenMethodName(),e);
     *
     * @param basicException
     * @return
     */
    @ExceptionHandler(BasicException.class)
    @ResponseBody
    public ResponseBean basicExceptionHandler(BasicException basicException) {
        basicException.getException().printStackTrace();
        return ResponseBean.FAIL();
    }

    /**
     * 获取子类的类名和方法名
     *
     * @return
     */
    public String getChildrenMethodName() {
        String clazz = Thread.currentThread().getStackTrace()[2].getClassName();
        String method = Thread.currentThread().getStackTrace()[2].getMethodName();
        return clazz + "." + method;
    }


}
