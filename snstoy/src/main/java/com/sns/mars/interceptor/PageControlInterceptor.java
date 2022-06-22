package com.sns.mars.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PageControlInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
		if(isAjax) {
			if(request.getParameter("param") != null) {
				StringBuffer url = request.getRequestURL().append("_copy/").append(request.getParameter("param"));
				System.out.println("url1 = " + url);
				response.sendRedirect(url.toString());
				return false;
			}else {
				StringBuffer url = request.getRequestURL().append("_copy");
				System.out.println("url2 = " + url);
				response.sendRedirect(url.toString());
				return false;
			}
		}else {
			return true;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	

}
