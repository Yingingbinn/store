package com.bin.dao;

import java.util.List;

import com.bin.pojo.category;

public interface categoryDao {

	List<category> findAll()throws Exception;

}
