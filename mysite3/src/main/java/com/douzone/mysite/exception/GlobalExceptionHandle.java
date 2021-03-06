package com.douzone.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.douzone.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

//AOP 코드가 작동
@ControllerAdvice
public class GlobalExceptionHandle {

	private static final Log LOG = LogFactory.getLog(GlobalExceptionHandle.class);

	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) throws ServletException, IOException {

		// 1. 로깅 작업
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOG.error(errors.toString());

		// 2. 시스템 오류 안내 페이지
		// ModelAndView mav=new ModelAndView();
		// mav.addObject("uri",request.getRequestURI());
		// mav.addObject("errors",errors.toString());
		// mav.setViewName("error/exception");
		//
		// return mav;

		String accept = request.getHeader("accept");
		if (accept.matches(".*application/json.*")) {

			response.setStatus(HttpServletResponse.SC_OK);
			OutputStream out=response.getOutputStream();
			out.write(new ObjectMapper().writeValueAsString(JSONResult.fail(errors.toString())).getBytes("utf-8"));
			out.flush();
			out.close();
			
			
		} else { //html 응답
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("errors", errors.toString());
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
		return null;

	}
}
