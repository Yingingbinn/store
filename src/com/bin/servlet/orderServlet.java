package com.bin.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bin.pojo.Cart;
import com.bin.pojo.User;
import com.bin.pojo.cartaItem;
import com.bin.pojo.order;
import com.bin.pojo.orderItem;
import com.bin.service.orderService;
import com.bin.service.Impl.orderServiceImpl;
import com.bin.utils.BeanFactory;
import com.bin.utils.UUIDUtils;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/order")
public class orderServlet extends baseServlet {
	
	public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//判断用户是否登录
		User user= (User) request.getSession().getAttribute("user");
		if(user==null){
			request.setAttribute("msg", "您还没登录请先登录哦~~");
			return "/jsp/msg.jsp";
		}
		//2封装数据
		order order=new order();
		//id
		order.setOid(UUIDUtils.getId());
		//订单时间
		order.setOrdertime(new Date());
		//总金额
		Cart cart= (Cart) request.getSession().getAttribute("cart");
		order.setTotal(cart.getTotal());
		//订单所有项
		for (cartaItem item : cart.getitems()) {
			orderItem oi =new orderItem();
			//设置id
			oi.setItemid(UUIDUtils.getId());
			//设置购买数量
			oi.setCount(item.getCount());
			//设置小计
			oi.setSubtotal(item.getSubtotal());
			//设置商品
			oi.setProduct(item.getP());
			//设置order
			oi.setOrder(order);
			// 添加到list当中
			order.getItems().add(oi);
		}
		//设置用户
		order.setUser(user);
		//调用service 
		orderService os=(orderService) BeanFactory.getBean("OrderService");
		os.add(order);
		
		// 将order放入request域中，请求转发
		request.setAttribute("bean", order);
		request.getSession().removeAttribute("cart");
		return "/jsp/order_info.jsp";
	}

}
