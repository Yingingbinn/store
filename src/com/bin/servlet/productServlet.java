package com.bin.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bin.pojo.PageBean;
import com.bin.pojo.product;
import com.bin.service.productService;
import com.bin.service.Impl.productServiceImpl;

/**
 * Servlet implementation class productServlet
 */
@WebServlet("/productServlet")
public class productServlet extends baseServlet {
	private static final long serialVersionUID = 1L;

	public String getBypid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid = request.getParameter("pid");
		productService ps =new productServiceImpl();
		product p = ps.getBypid(pid);
		request.setAttribute("bean", p);
		return "/jsp/product_info.jsp";
	}
	
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取类别当前页 设置pagesize
		String cid = request.getParameter("cid");
		int currPage =Integer.parseInt(request.getParameter("currPage")) ;
		int pageSize=12;
		//调用service返回 pagebean
		productService ps =new productServiceImpl();
		PageBean<product> pb = ps.findByPage(currPage,pageSize,cid);
		//将结果放入request中请求转发
		request.setAttribute("pb", pb);
		return "/jsp/product_list.jsp";
	}
}
