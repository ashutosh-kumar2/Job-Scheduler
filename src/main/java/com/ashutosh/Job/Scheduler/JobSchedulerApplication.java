package com.ashutosh.Job.Scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JobSchedulerApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(JobSchedulerApplication.class, args);
		for(String bean: ctx.getBeanDefinitionNames()){
			Object o = ctx.getBean(bean);
			if(o instanceof Worker){
				Worker worker = (Worker) o;
				new Thread(worker).start();
			}
		}
	}

}
