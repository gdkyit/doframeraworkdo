package com.gdky.restful.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.Expose;

@Component
public class Test {

	@Expose
	public String test(){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return "opk";
	}
}
