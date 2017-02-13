package com.boaglio.config;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.boaglio.cache.CacheManager;
import com.boaglio.validation.UserInfo;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String name = authentication.getName();
		String password = authentication.getCredentials().toString();

		// valid users:
		// user1/user1 
		// user2/user2 
		// user3/user3
		// ..
		if ( (name.startsWith("user") && password.equalsIgnoreCase(name))) {

			UserInfo userInfo = CacheManager.getUserInfoByLogin(name);
			if (userInfo != null)
				throw new RuntimeException("User " + userInfo.getLogin() + " already logged at " + userInfo.getDtStart());

			CacheManager.updateUserUUID(name,UUID.randomUUID().toString());

			return new UsernamePasswordAuthenticationToken(name,password,new ArrayList<>());
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
