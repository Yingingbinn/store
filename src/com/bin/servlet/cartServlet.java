package com.bin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bin.pojo.Cart;
import com.bin.pojo.cartaItem;
import com.bin.pojo.product;
import com.bin.service.productService;
import com.bin.utils.BeanFactory;


@WebServlet("/cart")
public class cartServlet extends baseServlet {
	public Cart getCart(HttpServletRequest request){
		Cart cart= (Cart) request.getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();	
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	private static final long serialVersionUID = 1L;
	public  String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取pid和count
		String pid =request.getParameter("pid");
		int count =Integer.parseInt( request.getParameter("count"));	
		//调用productservice 获取商品
		productService ps=(productService) BeanFactory.getBean("ProductService");
		product p=ps.getBypid(pid);
		//组装成CARTitEM
		cartaItem cartaItem=new cartaItem(p, count);
		Cart cart= getCart(request);
		cart.add2cart(cartaItem);
		//C重定向
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
}
	//从购物车删除 购物项
	public  String remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		getCart(request).removeFromCart(pid);
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
}
	public  String clear(HttpServletRequest request, HttpServletResponse response) throws Exception {
		getCart(request).clear();		
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
}
	}