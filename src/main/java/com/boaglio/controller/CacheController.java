package com.boaglio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boaglio.cache.CacheManager;
import com.boaglio.validation.UserInfo;

@Controller
public class CacheController {

 
	@GetMapping("/cache")
	public ModelAndView list() {
		
		List<UserInfo> loginList = CacheManager.getList();
		
		return new ModelAndView("loginList","loginList",loginList);
	}
 
	@GetMapping(value = "/cache/remove/{login}")
	public ModelAndView remover(@PathVariable("login") String login,RedirectAttributes redirect) {
		 
		boolean removed = CacheManager.removeUserUUID(login);
		
		System.out.println(" login ["+login+"] removed ? "+removed);
	 
		List<UserInfo> loginList = CacheManager.getList();
	
		return new ModelAndView("loginList","loginList",loginList);
	}
 
}
