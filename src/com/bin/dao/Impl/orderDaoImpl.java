package com.bin.dao.Impl;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.bin.dao.orderDao;
import com.bin.pojo.order;
import com.bin.pojo.orderItem;
import com.bin.utils.DataSourceUtils;

public class orderDaoImpl implements orderDao {
//添加订单
	@Override
	public void add(order order) throws Exception {
		QueryRunner qr= new QueryRunner();
		/*`oid` varchar(32) NOT NULL,
		  `ordertime` datetime DEFAULT NULL,
		  `total` double DEFAULT NULL,
		  `state` int(11) DEFAULT NULL,
		  `address` varchar(30) DEFAULT NULL,
		  `name` varchar(20) DEFAULT NULL,
		  `telephone` varchar(20) DEFAULT NULL,
		  `uid` varchar(32) DEFAULT NULL,*/
		String sql =" insert into orders() value (?,?,?,?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
				order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()
				);
		
	}
	//添加订单项
	@Override
	public void addItem(orderItem oi) throws Exception {
		QueryRunner qr= new QueryRunner();
		/*`itemid` varchar(32) NOT NULL,
		  `count` int(11) DEFAULT NULL,
		  `subtotal` double DEFAULT NULL,
		  `pid` varchar(32) DEFAULT NULL,
		  `oid` varchar(32) DEFAULT NULL,*/
		String sql ="insert into orderitem() value(?,?,?,?,?)";
		qr.update(DataSourceUtils.getConnection(),sql,oi.getItemid(),oi.getCount(),oi.getSubtotal(),oi.getProduct().getPid(),oi.getOrder().getOid() );
	}
		
}
