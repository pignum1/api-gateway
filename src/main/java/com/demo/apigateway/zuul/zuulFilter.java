package com.demo.apigateway.zuul;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * @author WXY
 * @ClassName zuulFilter
 * @Description T0D0
 * @Date 2019/5/28 17:05
 * @Version 1.0
 **/
public class zuulFilter extends com.netflix.zuul.ZuulFilter {
    /**
     *
     * fileType的返回值类型表示为过滤器的类型，过滤器的类型表示在那个生命周期执行
     * pre,post,error,route,static
     * pre表示的是在路由之前执行
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 表示过滤去的执行顺序（多个过滤器时有意义）
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     *表示过滤器是否执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 具体的过滤规则
     * @return
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String login = request.getParameter( "login" );
        if(login == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.addZuulResponseHeader("content-type","text/html;charset=utf-8");
            ctx.setResponseBody("非法访问");
        }
        return null;
    }
}