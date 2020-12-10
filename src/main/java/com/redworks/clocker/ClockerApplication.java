package com.redworks.clocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//
//import org.slf4j.LoggerFactory;
//import ch.qos.logback.access.PatternLayout;
//import ch.qos.logback.access.spi.IAccessEvent;
//import ch.qos.logback.access.tomcat.LogbackValve;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.core.ConsoleAppender;


@SpringBootApplication
public class ClockerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ClockerApplication.class, args);
	}

	//@Bean
	//public ServletWebServerFactory servletContainer() {
	//	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
	//	
	//	LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	//	
	//	PatternLayout patternLayout = new PatternLayout();
	//	patternLayout.setContext(lc);
	//	patternLayout.setPattern("common");
	//	patternLayout.start();
	//	
	//	ConsoleAppender<IAccessEvent> appender = new ConsoleAppender<>();
	//	appender.setContext(lc);
	//	appender.setLayout(patternLayout);
	//	appender.start();
	//	
//
	//	LogbackValve logbackValve = new LogbackValve();
	//	logbackValve.addAppender(appender);
	//	tomcat.addContextValves(logbackValve);
	//	return tomcat;
	//}

}