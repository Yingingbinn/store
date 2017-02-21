package com.bin.dao.Impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bin.dao.UserDao;
import com.bin.pojo.User;
import com.bin.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public void add(User user) throws SQLException {
		// TODO Auto-generated method stub
		 QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		 String sql="insert into user value(?,?,?,?,?,?,?,?,?,?);";	
		 
		 qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				 user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
	}

	@Override
	public User getuserBycode(String code) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		 String sql="select *from user where code =? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class),code);
	}

	@Override
	public void update(User user) throws Exception {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		 String sql="update user set username=?,password=?,name=?,email=?,birthday=?,state=?,code=? where uid=?";
		 qr.update(sql, user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				 user.getBirthday(),user.getState(),null,user.getUid());
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws Exception {
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		 String sql="select *from user where username =? and password=? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class),username,password);
	}

}
