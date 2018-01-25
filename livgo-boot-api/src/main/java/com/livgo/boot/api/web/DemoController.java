package com.livgo.boot.api.web;

import com.livgo.boot.api.service.DemoApiService;
import com.livgo.boot.common.exception.BasicException;
import com.livgo.boot.common.model.bean.ResponseBean;
import com.livgo.boot.model.entity.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Controller
@RequestMapping("demo")
public class DemoController extends BaseController {

    @Autowired
    private DemoApiService demoApiService;

    /**
     * 分页获取
     *
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list", produces = "application/json")
    public ResponseBean listDemo(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, HttpServletRequest request) {
        try {
            return ResponseBean.SUCCESS(demoApiService.listDemo(pageNum));
        } catch (Exception e) {
            throw new BasicException(getChildrenMethodName(), e);
        }
    }

    @RequestMapping(value = "view", produces = "application/json")
    public ModelAndView listDemoView(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("demoList");
            List<Demo> demoList = demoApiService.listDemo(pageNum);
            mav.addObject("demoList", demoList);
            return mav;
        } catch (Exception e) {
            throw new BasicException(getChildrenMethodName(), e);
        }
    }


}
