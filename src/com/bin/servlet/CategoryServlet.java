package com.bin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bin.pojo.category;
import com.bin.service.categoryService;
import com.bin.service.Impl.categoryServiceImpl;
import com.bin.utils.JsonUtil;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends baseServlet {
	
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 categoryService cs= new categoryServiceImpl();
		   List<category> clist=null;
		try {
			clist = cs.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将返回值转成json格式
		    //request.setAttribute("clist", clist);
		String json=JsonUtil.list2json(clist);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println(json);
		return null;
	}

}
