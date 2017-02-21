package com.bin.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bin.dao.categoryDao;
import com.bin.pojo.category;
import com.bin.utils.DataSourceUtils;

public class categoryDaoImpl implements categoryDao {

	@Override
	public List<category> findAll() throws Exception {
		QueryRunner qr= new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select * from category";
		return qr.query(sql, new BeanListHandler<>(category.class));
	}

}
