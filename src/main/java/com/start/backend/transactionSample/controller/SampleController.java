package com.start.backend.transactionSample.controller;

import org.springframework.web.bind.annotation.PathVariable;

import com.start.backend.transactionSample.vo.Sample;

public interface SampleController {

	// GET
	public String getSampleListByCondition(String sampleName, String condition);
	public String getSampleInfo(int sampleNum, Sample sample);
	public String checkSampleIdDuplicated(int sampleNum, Sample sample);

	// PUT
	String addSample(String sample);

	// POST
    String editSampleName(String path, Sample sample);
    String editSampleContents(String sampleContents);

    // DELETE
	String delSample(String sampleId) throws Exception;
	
}