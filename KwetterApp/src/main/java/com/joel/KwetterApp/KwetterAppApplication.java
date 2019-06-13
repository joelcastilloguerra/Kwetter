package com.joel.KwetterApp;

import com.joel.KwetterApp.utils.ViewScope;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.sun.faces.config.ConfigureListener;

import javax.faces.webapp.FacesServlet;
import java.util.HashMap;

@SpringBootApplication
public class KwetterAppApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication
				.run(KwetterAppApplication.class);

	}

	@Bean
	public static ViewScope viewScope() {
		return new ViewScope();
	}

	@Bean
	public static CustomScopeConfigurer scopeConfigurer() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("view", viewScope());
		configurer.setScopes(hashMap);
		return configurer;
	}

	@Bean
	public FacesServlet facesServlet() {
		return new FacesServlet();
	}

	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(
				facesServlet(), "*.xhtml");
		registration.setName("FacesServlet");
		return registration;
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<>(
				new ConfigureListener());
	}


}

