package com.boaglio.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.boaglio.cache.CacheManager;

@Component
public class CustomLogoutSuccessHandler extends
  SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
 
    @Override
    public void onLogoutSuccess
      (HttpServletRequest request, HttpServletResponse response, Authentication authentication) 
      throws IOException, ServletException {
        String refererUrl = request.getHeader("Referer");
        System.out.println("Logout from: " + refererUrl);
		String user = authentication.getName();
		System.out.println( " exiting = "+user);
		CacheManager.removeUserUUID(user);
		
        super.onLogoutSuccess(request, response, authentication);
    }
}