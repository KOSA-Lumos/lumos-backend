package com.start.backend.transactionSample.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.start.backend.transactionSample.service.SampleService;
import com.start.backend.transactionSample.vo.Sample;

@RestController
@CrossOrigin(origins = "http://localhost:8079", allowCredentials = "true", allowedHeaders = "Content-Type")
@RequestMapping(produces = "application/json; charset=utf-8")
public class SampleControllerImpl implements SampleController {

	private Logger log = LogManager.getLogger("case3");

	@Autowired
	private SampleService sampleService;

	
	
	
	
	// Constructors
	public SampleControllerImpl(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	
	
	
	
	// GET methods
	@Override
	@GetMapping(value="/sample/list/{sampleName}")
	public String getSampleListByCondition(@PathVariable String sampleName, String condition) {
		// TODO Auto-generated method stub
		System.out.println("@@ getSampleListByCondition 실행");
		return null;
	}

	@Override
	@GetMapping(value="/sample/info/{sampleNum}")
	public String getSampleInfo(@PathVariable int sampleNum, Sample sample) {
		// TODO Auto-generated method stub
		System.out.println("@@ getSampleInfo 실행");
		return null;
	}

	@Override
	@GetMapping(value="/sample/duplication/{sampleNum}")
	public String checkSampleIdDuplicated(@PathVariable int sampleNum, Sample sample) {
		// TODO Auto-generated method stub
		System.out.println("@@ checkSampleIdDuplicated 실행");
		return null;
	}

	
	
	// PUT methods
	@Override
	@PutMapping(value="/sample/newsample")
	public String addSample(String sample) {
		// TODO Auto-generated method stub
		System.out.println("@@ addSample 실행");
		return null;
	}

	
	
	// POST methods
	@Override
	@PostMapping(value="/sample/postmethod1")
	public String editSampleName(String path, Sample sample) {
		// TODO Auto-generated method stub
		System.out.println("@@ editSampleName 실행");
		return null;
	}

	@Override
	@PostMapping(value="/sample/postmethod2")
	public String editSampleContents(String sampleContents) {
		// TODO Auto-generated method stub
		System.out.println("@@ editSampleContents 실행");
		return null;
	}

	
	
	// DELETE methods
	@Override
	@DeleteMapping(value="/sample/delmethod")
	public String delSample(String sampleId) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("@@ delSample 실행");
		return null;
	}

}
