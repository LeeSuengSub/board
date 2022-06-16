package com.dw.board.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dw.board.VO.LogVO;
import com.dw.board.service.LogsService;

//Component : 개발자(내)가 직접 작성한 클래스를 스프링에게 빈(스프링이 관리하는 클래스)으로 등록하라는 뜻.
@Component
public class Interceptor implements HandlerInterceptor{
	
	private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
	
	@Autowired
	private LogsService logsService;

	//preHandle: 컨트롤러에 도착하기 전에 요청을 가로채는 함수
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		String ip = request.getHeader("x-Forwarded-for");
		String httpMethod = request.getMethod();
		if(ip == null) ip = request.getRemoteAddr();
		
		logger.info("client IP : "+ip);
		logger.info("request URL : "+url);
		logger.info("client HTTP httpMethod : "+httpMethod);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);//한국시간으로 강제로 맞춤
		String time = formatter.format(Calendar.getInstance().getTime());
		
		LogVO vo = new LogVO();
		vo.setUrl(url);
		vo.setIp(ip);
		vo.setHttpMethod(httpMethod);
		vo.setLatitude("36.3286904");
		vo.setLongitude("127.4229992");
		vo.setCreateAt(time);
		logsService.setLogs(vo);
		
		//세션 체크
		HttpSession session = request.getSession();
//		if(session.getAttribute("studentsId") != null) {
//		int studentsId = (int)session.getAttribute("studentsId");
//		String studentsName = (String)session.getAttribute("students_name");
//		System.out.println("세션에서 가져온 id==>"+ studentsId);
//		System.out.println("세션에서 가져온 name==>"+ studentsName);
//		}
		if(session.getAttribute("studentsId")==null) {
			logger.info("session studentsId:"+session.getAttribute("studentsId"));
			response.sendRedirect("/login");//세션에 값이 없으면 /login 경로로 redirect!
			return false;
		}
		
		return true;
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
