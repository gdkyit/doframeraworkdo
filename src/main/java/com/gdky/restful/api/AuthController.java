package com.gdky.restful.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.bstek.dorado.annotation.Expose;

@Controller
public class AuthController {

	@Expose
	public String test() {
		return "OK";
	}


}