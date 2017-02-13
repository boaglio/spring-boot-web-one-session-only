package com.boaglio.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.boaglio.cache.CacheManager;

public class ModelHandlerInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public void postHandle(final HttpServletRequest request,final HttpServletResponse response,final Object handler,final ModelAndView modelAndView) throws Exception {
 
		long threadId = Thread.currentThread().getId();
		String url= request.getRequestURL().toString();
		
		System.out.println(threadId + " >> " +url);
		
		String uuid = (String) modelAndView.getModel().get("uuid");
		
		if (url.endsWith("hello"))  {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String name = auth.getName();
			System.out.println(threadId + " name >> " + name);
	
			if (CacheManager.hasUserUUID(name,uuid)) {
				System.out.println(" already logged user! login="+name+" uuid="+uuid);
				 
			}
		
		}
		 
	}

}
