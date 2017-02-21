package com.bin.dao;

import java.sql.SQLException;

import com.bin.pojo.User;

public interface UserDao {


	void add(User user) throws Exception;

	User getuserBycode(String code)throws Exception;

	void update(User user)throws Exception;

	User getUserByUsernameAndPassword(String username, String password)throws Exception;


}
