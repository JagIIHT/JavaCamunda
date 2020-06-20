package com.example.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("processDelegate")
public class ProcessAppDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		String status = (String) execution.getVariable("AppStatus");
		execution.setVariable("AppStatus", status + " - Application Completed");
	}

}
