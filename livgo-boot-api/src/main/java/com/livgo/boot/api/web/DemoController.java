package com.livgo.boot.api.web;

import com.livgo.boot.api.service.DemoService;
import com.livgo.boot.common.exception.BasicException;
import com.livgo.boot.common.model.bean.ResponseBean;
import com.livgo.boot.model.entity.Demo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
@Api(tags = "Demo测试")
@Controller
@RequestMapping("demo")
public class DemoController extends BaseController {

    @Autowired
    private DemoService demoService;

    /**
     * 分页获取
     *
     * @param pageNum
     * @return
     */
    @ApiOperation("分页获取")
    @ResponseBody
    @GetMapping(value = "list1", produces = "application/json")
    public ResponseBean list1Demo(@ApiParam("页码") @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, HttpServletRequest request) {
        try {
            return ResponseBean.SUCCESS(demoService.list1Demo(pageNum));
        } catch (Exception e) {
            throw new BasicException(getChildrenMethodName(), e);
        }
    }

    /**
     * 返回结果数
     *
     * @param pageNum
     * @return
     */
    @ApiOperation("分页获取")
    @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "Integer", paramType = "query")
    @ResponseBody
    @PostMapping(value = "list2", produces = "application/json")
    public ResponseBean list2Demo(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, HttpServletRequest request) {
        try {
            return ResponseBean.SUCCESS(demoService.list2Demo());
        } catch (Exception e) {
            throw new BasicException(getChildrenMethodName(), e);
        }
    }

    /**
     * 返回Freemark视图
     * ApiIgnore, 忽视Swagger文档
     *
     * @param pageNum
     * @param request
     * @return
     */
    @ApiIgnore
    @RequestMapping(value = "view1", produces = "application/json")
    public ModelAndView listDemoView1(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("demoList");
            List<Demo> demoList = demoService.listDemo(pageNum);
            mav.addObject("demoList", demoList);
            return mav;
        } catch (Exception e) {
            throw new BasicException(getChildrenMethodName(), e);
        }
    }

    /**
     * 返回Freemark视图
     * 会被自动Swagger
     *
     * @param pageNum
     * @param request
     * @return
     */
    @RequestMapping(value = "view2", produces = "application/json")
    public ModelAndView listDemoView2(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("demoList");
            List<Demo> demoList = demoService.listDemo(pageNum);
            mav.addObject("demoList", demoList);
            return mav;
        } catch (Exception e) {
            throw new BasicException(getChildrenMethodName(), e);
        }
    }

}
