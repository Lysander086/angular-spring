package com.example.howtodoinjava.springeurekaclientschoolservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class SchoolServiceController {
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/getSchoolDetails/{schoolName}", method = RequestMethod.GET)
	public String getStudents(@PathVariable String schoolName) {
		log.info("Getting School details for " + schoolName);
		String response = restTemplate.exchange("http://student-service/getStudentDetailsForSchool/{schoolName}", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
		}, schoolName).getBody();

		log.info("Response Received as " + response);

		return "School Name -  " + schoolName + " \n Student Details " + response;
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
