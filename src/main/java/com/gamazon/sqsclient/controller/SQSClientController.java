package com.gamazon.sqsclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ListQueuesRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;

@RestController
public class SQSClientController 
{
	
	
	@GetMapping("/call")
	public void callSQS()
	{
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration("http://localhost:4566/", "us-west-1");
		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withEndpointConfiguration(endpointConfiguration).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("",""))).build();
		ListQueuesResult list = sqs.listQueues();
		
		for(String url:list.getQueueUrls())
		{
			System.out.println(url);
		}
		
	}
	

}
