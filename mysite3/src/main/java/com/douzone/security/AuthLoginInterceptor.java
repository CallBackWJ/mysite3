package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		//ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		//UserService userService=ac.getBean(UserService.class);
		
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		UserVo vo=userService.getUser(email,password);
		if(vo==null)
		{
			//인증실패
			request.setAttribute("result", "fail");
			response.sendRedirect(request.getContextPath()+"/user/login?result=fail");
		
			
			return false;
		}
		//인증성공 -> 인증처리
		HttpSession session=request.getSession(true);
		session.setAttribute("authuser", vo);
		
		response.sendRedirect(request.getContextPath()+"/");
		return false;
	}

	
}
