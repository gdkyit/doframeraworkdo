package com.gdky.restful.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.bstek.dorado.web.filter.DelegatingFilterProxy;
import com.bstek.dorado.web.servlet.DoradoServlet;
import com.bstek.dorado.web.servlet.SpringContextLoaderListener;

public class WebInit implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		// TODO Auto-generated method stub
		/*
		 * If no active profile is set via -Dspring.profiles.active or some
		 * other mechanism then this will be used.
		 */
		container.setInitParameter("contextConfigLocation", "/WEB-INF/spring/*.xml,/WEB-INF/dorado-home/*.xml");

		// Creates the root application context
		//AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		//appContext.setDisplayName("Silver Chalice Web API");

		// Registers the application configuration with the root context
		// appContext.register(AppConfig.class);

		// Creates the Spring Container shared by all Servlets and Filters
		// container.addListener(new ContextLoaderListener(appContext));
		container.addListener(new SpringContextLoaderListener());
		container.addListener(new Log4jConfigListener());

		Dynamic delegatingFilterProxy = container.addFilter("delegatingFilterProxy", DelegatingFilterProxy.class);
		delegatingFilterProxy.addMappingForUrlPatterns(null, false,
				"/*");
		Map<String,String> para = new HashMap<String,String>();
		para.put("encoding", "UTF-8");
		para.put("forceEncoding", "true");
		Dynamic characterEncodingFilter = container.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
		characterEncodingFilter.setInitParameters(para);
		characterEncodingFilter.addMappingForUrlPatterns(null, false,
				"/*");
		Map<String,String> dpara = new HashMap<String,String>();
		dpara.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		Dynamic DruidWebStatFilter = container.addFilter("DruidWebStatFilter", WebStatFilter.class);
		DruidWebStatFilter.setInitParameters(dpara);
		DruidWebStatFilter.addMappingForUrlPatterns(null, false,
				"/*");
				
		ServletRegistration.Dynamic rest =container.addServlet("rest",new DispatcherServlet());
		Map<String,String> restpara = new HashMap<String,String>();
		restpara.put("contextConfigLocation", "/WEB-INF/spring/rest-servlet.xml");
		rest.setInitParameters(restpara);
		rest.setLoadOnStartup(1);
		rest.addMapping("/");
		
		ServletRegistration.Dynamic doradoServlet =container.addServlet("doradoServlet",new DoradoServlet());
		doradoServlet.setLoadOnStartup(1);
		doradoServlet.addMapping("*.d","*.c","*.dpkg","/dorado/*");

		Map<String,String> druidpara = new HashMap<String,String>();
		druidpara.put("resetEnable", "true");
		druidpara.put("loginUsername", "user");
		druidpara.put("loginPassword", "123");
		ServletRegistration.Dynamic DruidStatView =container.addServlet("DruidStatView",new StatViewServlet());
		DruidStatView.addMapping("/druid/*");
		DruidStatView.setInitParameters(druidpara);
	}

}