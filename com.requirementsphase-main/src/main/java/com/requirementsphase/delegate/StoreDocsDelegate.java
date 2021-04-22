package com.requirementsphase.delegate;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("storeDocs")
public class StoreDocsDelegate implements JavaDelegate {
	@Value("${arydb.host}")
	String aryadbHost;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailEvaluationDelegate.class);
	
	public void execute(DelegateExecution execution) throws Exception {
		LOGGER.info(execution.getCurrentActivityName());
		String nameDocRegistry=(String)execution.getVariable("nameDocRegistry");
		String summaryDocRegistry=(String)execution.getVariable("summaryDocRegistry");
		String descriptionDocRegistry=(String)execution.getVariable("descriptionDocRegistry");
		String reportDocRegistry=(String)execution.getVariable("reportDocRegistry");
		String priorityDocRegistry=(String)execution.getVariable("priorityDocRegistry");
		String tagsDocRegistry=(String)execution.getVariable("tagsDocRegistry");
        RestTemplate restTemplate = new RestTemplate();

		String url = aryadbHost+"insertStory";
		String requestJson="{\"name\":\""+nameDocRegistry+"\",\"summary\":\""+summaryDocRegistry+"\",\"description\":\""+descriptionDocRegistry+"\",\"owner\":\""+reportDocRegistry+"\",\"priority\":\""+priorityDocRegistry+"\",\"assigned\":\""+tagsDocRegistry+"\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
		String answer = restTemplate.postForObject(url, entity, String.class);
		LOGGER.info(answer);
		
	}

}
