package br.com.caelum.tarefas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter{

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String uri = request.getRequestURI();
		
		if(uri.endsWith("login") || uri.endsWith("efetuaLogin") || uri.contains("resources"))
		{
			return true;
		}
		
		if(request.getSession().getAttribute("usuarioLogado") != null)
		{
			return true;
		}
		
		response.sendRedirect("login");
		return false;
	}
}
