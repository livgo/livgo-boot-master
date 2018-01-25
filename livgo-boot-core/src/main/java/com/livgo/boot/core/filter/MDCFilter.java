package com.livgo.boot.core.filter;

import com.livgo.boot.common.Const;
import com.livgo.boot.common.util.lang.UUIDUtil;
import org.apache.log4j.MDC;

import javax.servlet.*;
import java.io.IOException;

/**
 * Description:MDC过滤(添加日志sessionid)
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class MDCFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        MDC.put("REQUESTID", UUIDUtil.get32UUID());
        response.setCharacterEncoding(Const.ENCODE);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fc) throws ServletException {

    }

    public void destroy() {

    }
}
