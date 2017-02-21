package com.bin.dao.Impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bin.dao.productDao;
import com.bin.pojo.product;
import com.bin.utils.DataSourceUtils;

public class productDaoImpl implements productDao {

	@Override
	public List<product> findnew() throws Exception {
		QueryRunner qr =new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select *from product order by pdate limit 9";
		return qr.query(sql, new BeanListHandler<>(product.class)  );
	}

	@Override
	public List<product> findhot() throws Exception {
		QueryRunner qr =new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select *from product where is_hot=1 order by pdate limit 9 ";
		 return qr.query(sql, new BeanListHandler<>(product.class)  );
	}

	@Override
	public product getBypid(String pid) throws Exception {
		QueryRunner qr =new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select *from product where pid=? limit 1 ";
		 return qr.query(sql, new BeanHandler<>(product.class), pid);
	}

	@Override
	//查询当前类别的总条数
	public int findtotalCount(String cid) throws Exception {
		QueryRunner qr =new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select count( *)from product where cid=?  ";
		return ((Long) qr.query(sql, new ScalarHandler(), cid)).intValue();
	}

	@Override
	//查询当前要查询的数据
	public List<product> findByPage(int currPage, int pageSize, String cid) throws Exception {
		QueryRunner qr =new QueryRunner(DataSourceUtils.getDataSource());
		String sql="select *from product where cid=? limit ?,? ";
		return qr.query(sql, new BeanListHandler<>(product.class),cid,(currPage-1)*pageSize,pageSize);
	}

	

}
