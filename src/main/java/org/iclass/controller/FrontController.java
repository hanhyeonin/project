package org.iclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns= {"/"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
	
	@Override
	public void init() throws ServletException {
		RequestControllerMapping.init();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {   //Get,Post 방식 둘다 처리
		String url = request.getServletPath();
		String method = request.getMethod();
		
		RequestKeyValue key = new RequestKeyValue(url, method);
		Controller controller = RequestControllerMapping.getController(key);
		if(controller != null) { 
			logger.info("::::::::::{}-{}::::::::::",key,controller.getClass());
			controller.handle(request, response);
		}
}
}
