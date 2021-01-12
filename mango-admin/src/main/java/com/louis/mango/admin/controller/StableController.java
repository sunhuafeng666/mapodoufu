package com.louis.mango.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.MainResponse;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.SelectStableResponse;
import com.louis.mango.admin.model.Stable;
import com.louis.mango.admin.service.StableService;

@RestController // Controller
@RequestMapping("/")
@Transactional
public class StableController {

	@Autowired
	StableService stableService;

	@RequestMapping("/addStbale")
	public ProcessingResult insertStbale(Stable stable) {
		ProcessingResult processingResult = new ProcessingResult();
		try {
			processingResult = stableService.insertStable(stable);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			processingResult.setResultId("C001");
			processingResult.setResultContent("ControllerErrpr" + e.getMessage());
		}
		return processingResult;
	}
	
	@RequestMapping("/deleteStbale")
	public ProcessingResult deleteStbale(Integer id) {
		ProcessingResult processingResult = new ProcessingResult();
		try {
			processingResult = stableService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			processingResult.setResultId("C001");
			processingResult.setResultContent("ControllerErrpr" + e.getMessage());
		}
		return processingResult;
	}
	
	@RequestMapping("/selectAllStable")
	SelectStableResponse selectAllStable() {
		SelectStableResponse selectStableInfoResponse = new SelectStableResponse();
		try {
			selectStableInfoResponse = stableService.selectAllStable();
		} catch (Exception e) {
			e.printStackTrace();
			selectStableInfoResponse.setResultId("E001");
			selectStableInfoResponse.setResultContent("SYSTEM ERROR2");
		}
		return selectStableInfoResponse;
	}
	
	@RequestMapping("/selectStableByIdName")
	MainResponse selectStableByIdName(String idName) {
		MainResponse selectStableInfoResponse = new MainResponse();
		try {
			selectStableInfoResponse = stableService.selectAllStableByIdname(idName);		
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			selectStableInfoResponse.setResultId("C001");
			selectStableInfoResponse.setResultContent("ControllerErrpr" + e.getMessage());
		}
		return selectStableInfoResponse;
	}
	
}
