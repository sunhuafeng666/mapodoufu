package com.louis.mango.admin.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.louis.mango.admin.dao.UserMapper;
import com.louis.mango.admin.model.LoginResponse;
import com.louis.mango.admin.model.ProcessingResult;
import com.louis.mango.admin.model.User;
import com.louis.mango.admin.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper UserMapper;

	@Override
	public List<User> selectAll() {

		return UserMapper.selectAll();
	}

	// 新增用户
	@Override
	public ProcessingResult insert(User record) {
		// TODO Auto-generated method stub
		ProcessingResult pr = new ProcessingResult();
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			// 通过电话号查询用户，为了查重
			List<User> list = UserMapper.selectByPhone(record.getPhone());

			if (list.size() > 0) {
				pr.setResultId("N003");
				pr.setResultContent("电话号已注册！");
				return pr;
			}
			list = UserMapper.selectByNickname(record.getNickname());
			if (list.size() > 0) {
				pr.setResultId("N003");
				pr.setResultContent("名字已注册！");
				return pr;
			}

			User insertUser = new User();
			// 删除默认为0
			insertUser.setIsDelete(0);
			// 默认等级为1
			insertUser.setLevel(1);
			// 游戏名字
			insertUser.setNickname(record.getNickname());
			// 密码
			insertUser.setPassword(record.getPassword());
			// 电话号
			insertUser.setPhone(record.getPhone());
			// 注册时间
			insertUser.setRegistTime(df.format(new Date()));
			// 进行插入用户
			int insertResult = UserMapper.insert(insertUser);
			// 成功的场合
			if (insertResult == 1) {
				pr.setResultId("N001");
				pr.setResultContent("创建用户成功！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			pr.setResultId("D001");
			pr.setResultContent("错误信息" + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return pr;
	}

	// 登录用
	@Override
	public LoginResponse loginProcessingResult(User user) {
		// TODO Auto-generated method stub
		LoginResponse pr = new LoginResponse();
		try {

			List<User> list = UserMapper.selectByPhoneAndPassword(user.getPhone(), user.getPassword());
			// 登录名字或者密码不对
			if (list.isEmpty()) {
				pr.setResultId("E001");
				pr.setResultContent("密码或者电话不对");
			} else {
				pr.setResultId("N001");
				pr.setResultContent("登录成功");
				list.get(0).setPassword(null);
				pr.setUserInfoList(list);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			pr.setResultId("D001");
			pr.setResultContent("错误信息：" + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return pr;
	}
	//通过电话查询到用户
	@Override
	public LoginResponse selectByPhone(String phone) {
		// TODO Auto-generated method stub
		LoginResponse lResponse = new LoginResponse();
		try {
			List<User> list = UserMapper.selectByPhone(phone);
			if (list.size() > 0) {
				lResponse.setResultId("N001");
				lResponse.setResultContent("查询成功！");
				lResponse.setUserInfoList(list);
			} else {
				lResponse.setResultId("N002");
				lResponse.setResultContent("没有改手机号的用户！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			lResponse.setResultId("D001");
			lResponse.setResultContent("错误信息：" + e.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return lResponse;
	}

}
