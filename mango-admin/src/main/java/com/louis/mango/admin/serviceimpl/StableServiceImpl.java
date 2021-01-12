package com.louis.mango.admin.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.louis.mango.admin.dao.MainMapper;
import com.louis.mango.admin.dao.StableInfoMapper;
import com.louis.mango.admin.dao.StableMapper;
import com.louis.mango.admin.model.Main;
import com.louis.mango.admin.model.MainResponse;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.SelectStableResponse;
import com.louis.mango.admin.model.Stable;
import com.louis.mango.admin.service.StableService;

@Service
@Transactional
public class StableServiceImpl implements StableService {

	@Autowired
	StableMapper stableMapper;
	@Autowired
	StableInfoMapper stableInfoMapper;
	@Autowired
	MainMapper mainMapper;
	// 活动报名
	@Override
	public ProcessingResult insertStable(Stable stable) {
		// TODO Auto-generated method stub
		ProcessingResult processingResult = new ProcessingResult();

		try {
			List<Stable> list = stableMapper.selectStablesByNicknameAndIdname(stable.getNickname(), stable.getIdname(),
					stable.getIdnameteamnum());
			if (list.size() > 0) {
				processingResult.setResultId("N003");
				processingResult.setResultContent("已报名");
			} else {
				int num = stableMapper.insertStable(stable);
				if (num == 1) {
					processingResult.setResultId("N001");
					processingResult.setResultContent("报名成功");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processingResult.setResultId("D001");
			processingResult.setResultContent("错误信息：" + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return processingResult;
	}

	// 取消报名
	@Override
	public ProcessingResult deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		ProcessingResult processingResult = new ProcessingResult();
		try {
			List<Stable> list = stableMapper.selectByPrimaryKey(id);
			if (list.isEmpty()) {
				processingResult.setResultId("N003");
				processingResult.setResultContent("未报名");
			} else {
				int num = stableMapper.deleteByPrimaryKey(id);
				if (num == 1) {
					processingResult.setResultId("N001");
					processingResult.setResultContent("报名取消成功");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			processingResult.setResultId("D001");
			processingResult.setResultContent("错误信息：" + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return processingResult;
	}

	// 查询所有报名数据
	@Override
	public SelectStableResponse selectAllStable() {
		// TODO Auto-generated method stub
		SelectStableResponse allStable = new SelectStableResponse();
		try {
			List<Stable> list = stableMapper.selectAll();
			if (list == null) {
				allStable.setResultId("E001");
				allStable.setResultContent("DB ERROR");
			} else if (list.isEmpty()) {
				allStable.setResultContent("N003");
				allStable.setResultContent("无数据");
			} else {
				allStable.setResultId("N001");
				allStable.setResultContent("成功查询全部数据");
				allStable.setStableInfoList(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			allStable.setResultId("D001");
			allStable.setResultContent("错误信息：" + e.getMessage());
		}
		return allStable;
	}

	// 通过活动名字查询报名数据
	@Override
	public MainResponse selectAllStableByIdname(String idName) {
		// TODO Auto-generated method stub
		MainResponse mainResponse = new MainResponse();
		try {
			// 该活动所有信息
			List<Main> stableInfoList = mainMapper.selectByIdNameAndTeamNum(idName);
			// 该活动所有人数信息
			mainResponse.setMainList(stableInfoList);
			mainResponse.setResultId("N001");
			mainResponse.setResultContent("成功查询全部数据");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			mainResponse.setResultId("D001");
			mainResponse.setResultContent("错误信息：" + e.getMessage());
		}
		return mainResponse;
	}

}
