package com.louis.mango.admin.model;

public class ProcessingResult {
	
	private String resultId;
	private String resultContent;
	
	public ProcessingResult() {
		this.resultId="";
		this.resultContent="";
	}
	
	public ProcessingResult(String resultId,String resultContent) {
		this.resultId="";
		this.resultContent="";
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getResultContent() {
		return resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}
	
	
}
