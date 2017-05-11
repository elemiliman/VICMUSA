package org.vicmusa.church.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RequestMonitor {

	private static final Log log = LogFactory.getLog(RequestMonitor.class);
	
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object wrap(ProceedingJoinPoint pjp) throws Throwable {
    	
		log.info("Before controller method " + pjp.getSignature().getName() + ". Thread " + Thread.currentThread().getName()); // toString gives more info    	
    	Object retVal = pjp.proceed();
    	log.info("Controller method " + pjp.getSignature().getName() + " execution successful");
	    
	    return retVal;
	    
	}
}
