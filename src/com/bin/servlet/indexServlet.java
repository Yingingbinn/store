package com.bin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bin.pojo.product;
import com.bin.service.productService;
import com.bin.service.Impl.productServiceImpl;


/**
 * Servlet implementation class index
 */
@WebServlet("/index")
public class indexServlet extends baseServlet {
	
	public  String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		productService ps=new productServiceImpl();
		List<product> listnew=null;
		List<product> listhot=null;
		try {
			listnew = ps.findnew();
			listhot=ps.findhot();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		request.setAttribute("listn", listnew);
		request.setAttribute("listh", listhot);
		
		
		return "/jsp/index.jsp";
	}

}
