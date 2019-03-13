package com.douzone.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.SiteVo;

public class SiteInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		ServletContext sc=request.getServletContext();
		ModelMap m= modelAndView.getModelMap();
		SiteVo vo=(SiteVo) m.get("site");
		
		if(vo!=null)
		{
			
			sc.setAttribute("site", vo);
		}
	}
}
