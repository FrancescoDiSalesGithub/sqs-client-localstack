package com.gamazon.sqsclient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.gamazon.sqsclient.model.AWSRequestSQSClient;

@RestController
public class SQSClientController 
{
	
	
	@GetMapping("/local")
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
	
	@PostMapping("/custom")
	public List<String> queues(@RequestBody AWSRequestSQSClient client )
	{
		EndpointConfiguration endpointConfiguration = new EndpointConfiguration(client.getServiceEndpoint(), client.getRegion());
		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withEndpointConfiguration(endpointConfiguration).withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(client.getAccessKey(), client.getSecretKey()))).build();
		ListQueuesResult list = sqs.listQueues();
		
		List<String> urlQueue = new ArrayList<String>();
		
		for(String url:list.getQueueUrls())
		{
			urlQueue.add(url);
		}
		
		return urlQueue;
	}
	

}
