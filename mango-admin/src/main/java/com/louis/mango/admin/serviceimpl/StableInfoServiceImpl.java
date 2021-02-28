package com.louis.mango.admin.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.louis.mango.admin.dao.StableInfoMapper;
import com.louis.mango.admin.dao.StableMapper;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.SelectAllStableNameResponse;
import com.louis.mango.admin.model.SelectStableNameInfoResponse;
import com.louis.mango.admin.model.Stable;
import com.louis.mango.admin.model.StableInfo;
import com.louis.mango.admin.service.StableInfoService;

@Service
@Transactional
public class StableInfoServiceImpl implements StableInfoService {

	@Autowired
	StableInfoMapper stableInfoMapper;
	
	@Autowired
	StableMapper stableMapper;
	// 所有活动
	@Override
	public SelectStableNameInfoResponse selectAllStableInfos() {
		// TODO Auto-generated method stub
		SelectStableNameInfoResponse selectAllStableName = new SelectStableNameInfoResponse();
		try {
			List<StableInfo> list = stableInfoMapper.selectAll();
			if (list.isEmpty()) {
				selectAllStableName.setResultId("N003");
				selectAllStableName.setResultContent("表分类无数据");
			} else {
				selectAllStableName.setResultId("N001");
				selectAllStableName.setResultContent("成功查询全部表分类数据");
				selectAllStableName.setList(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			selectAllStableName.setResultId("D001");
			selectAllStableName.setResultContent("错误信息：" + e.getMessage());
		}
		return selectAllStableName;
	}

	// 新增活动
	@Override
	public ProcessingResult insertStableInfo(StableInfo AddStableInfo) {
		// TODO Auto-generated method stub
		ProcessingResult processingResult = new ProcessingResult();
		try {
			List<StableInfo> list = stableInfoMapper.selectByIdName(AddStableInfo.getIdname());
			StableInfo stableInfo = new StableInfo();
			//还没有该活动
			if (list.isEmpty()) {
				stableInfo.setIdnameteamnum(1);
				// 活动已经有了，增加副序号
			} else {
				// 默认副序号+1
				stableInfo.setIdnameteamnum(list.get(0).getIdnameteamnum() + 1);
			}// 活动名字
			stableInfo.setIdname(AddStableInfo.getIdname());
			// 默认没有删除
			stableInfo.setIsDelete(0);
			// 活动备注
			stableInfo.setMemo(AddStableInfo.getMemo());
			// 活动人数
			stableInfo.setPeoplenum(AddStableInfo.getPeoplenum());
			// 活动创建者
			stableInfo.setRegister(AddStableInfo.getRegister());
			// 活动创建时间
			stableInfo.setRegisttime(LocalDateTime.now().withNano(0));
			// 活动开始时间
			if (AddStableInfo.getStarttime() != null) {
				stableInfo.setStarttime(AddStableInfo.getStarttime());
			}	
			int num = stableInfoMapper.insert(stableInfo);
			if (num == 1) {
				Stable stable= new Stable();
				stable.setIdname(stableInfo.getIdname());
				stable.setIdnameteamnum(stableInfo.getIdnameteamnum());
				stable.setNickname(stableInfo.getRegister());
				int num2 = stableMapper.insertStable(stable);
				if (num2 == 1) {
					processingResult.setResultId("N001");
					processingResult.setResultContent("表创建成功");
				}else {
					processingResult.setResultId("E001");
					processingResult.setResultContent("失败");
				}
			}else {
				processingResult.setResultId("E001");
				processingResult.setResultContent("表创建失败");
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
	//得到所有活动的名字
	@Override
	public SelectAllStableNameResponse selectAllStableIdname() {
		// TODO Auto-generated method stub
		SelectAllStableNameResponse allNameResponse = new SelectAllStableNameResponse();
		try {
			List<String> list = stableInfoMapper.selectAllIdName();
			allNameResponse.setResultId("N001");
			allNameResponse.setResultContent("查询成功");
			allNameResponse.setList(list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			allNameResponse.setResultId("D001");
			allNameResponse.setResultContent("错误信息：" + e.getMessage());
		}
		return allNameResponse;
	}

}
