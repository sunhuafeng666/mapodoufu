package com.louis.mango.admin.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.SelectAllStableNameResponse;
import com.louis.mango.admin.model.SelectStableNameInfoResponse;
import com.louis.mango.admin.model.StableInfo;
import com.louis.mango.admin.model.StableInfoRequestBean;
import com.louis.mango.admin.service.StableInfoService;

@RestController 
@RequestMapping("/")
@Transactional
public class StableInfoController {

	@Autowired
	StableInfoService stableInfoService;

	@RequestMapping("/selectAllStableInfo")
	SelectStableNameInfoResponse selectAllStableInfo() {

		SelectStableNameInfoResponse selectStableNameInfoResponse = new SelectStableNameInfoResponse();

		try {
			selectStableNameInfoResponse = stableInfoService.selectAllStableInfos();
			List<StableInfo> list = selectStableNameInfoResponse.getList().stream()
	                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> 
	                new TreeSet<>(Comparator.comparing(StableInfo::getIdname))), ArrayList::new));
			selectStableNameInfoResponse.setList(list);
			if (selectStableNameInfoResponse.getResultId().equals("D001")) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			selectStableNameInfoResponse.setResultId("C001");
			selectStableNameInfoResponse.setResultContent("ControllerErrpr" + e.getMessage());
		}

		return selectStableNameInfoResponse;
	}

	@RequestMapping("/insertStableInfo")
	ProcessingResult insertStableInfo(@Validated StableInfoRequestBean stableInfoRequestBean) {
		ProcessingResult processingResult = new ProcessingResult();
		StableInfo stableInfo = new StableInfo();
		stableInfo.setIdname(stableInfoRequestBean.getIdname());
		stableInfo.setMemo(stableInfoRequestBean.getMemo());
		stableInfo.setStarttime(stableInfoRequestBean.getStarttime());
		stableInfo.setPeoplenum(stableInfoRequestBean.getPeoplenum());
		stableInfo.setRegister(stableInfoRequestBean.getRegister());
		try {
			processingResult = stableInfoService.insertStableInfo(stableInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processingResult.setResultId("C001");
			processingResult.setResultContent("ControllerErrpr" + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return processingResult;
	}
	
	@RequestMapping("/getAllIdName")
	SelectAllStableNameResponse getAllIdName() {
		SelectAllStableNameResponse processingResult = new SelectAllStableNameResponse();
		try {
			processingResult = stableInfoService.selectAllStableIdname();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processingResult.setResultId("C001");
			processingResult.setResultContent("ControllerErrpr" + e.getMessage());
		}
		return processingResult;
	}

}
