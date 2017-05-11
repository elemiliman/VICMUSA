package org.vicmusa.church;

import java.util.Arrays;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class VicmusaApplication extends SpringBootServletInitializer {

	private static final Log log = LogFactory.getLog(VicmusaApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(VicmusaApplication.class, args);
		
		log.info("Beans in application context:");
		
		String beanNames[] = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		
		for(String beanName: beanNames){
			log.info(beanName);
		}
	}
}
