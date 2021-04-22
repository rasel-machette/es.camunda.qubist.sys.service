package com.requirementsphase.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("sendEmails")
public class SendEmailsDelegate implements ExecutionListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailsDelegate.class);

	public void notify(DelegateExecution execution) throws Exception {
		LOGGER.info("SendEmailsDelegate");
	}

}
