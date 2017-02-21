package com.bin.service.Impl;

import com.bin.dao.UserDao;
import com.bin.dao.Impl.UserDaoImpl;
import com.bin.pojo.User;
import com.bin.service.UserService;
import com.bin.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	public void regist(User user) throws Exception{
		// TODO Auto-generated method stub
		UserDao userDao =new UserDaoImpl();
		userDao.add(user);
		//发送邮件
		String emailMsg="欢迎您注册成为我们的一员，<a href ='http://localhost:8080/store/user?method=active&code="+ user.getCode()+"'>点此激活<a/>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	@Override
	public User active(String code) throws Exception {
		// TODO Auto-generated method stub
		//1通过code获取一个用户
		UserDao userDao =new UserDaoImpl();
		User user =userDao.getuserBycode(code);
		//2判断用户是否为空
		if(user==null){
			return null;
					}
		//3:修改用户状态
		user.setState(1);
		userDao.update(user);
		return user;
	}

	@Override
	public User login(String username, String password) throws Exception {
		UserDao userDao =new UserDaoImpl();
		return userDao.getUserByUsernameAndPassword(username,password);
		
	}

	

}
