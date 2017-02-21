package com.bin.servlet;


import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class baseServlet
 */
@WebServlet("/baseServlet")
public class baseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	public  void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			//1，获取子类
		
		Class clazz=this.getClass();
		//2，获取请求方法
		String m=req.getParameter("method");
		if(m==null){
			m="index";
		}
		//3获取方法对象
		
			Method method= clazz.getMethod(m, HttpServletRequest.class,HttpServletResponse.class);
		
		//4.让方法执行 返回值为请求转发路径
			String s=(String) method.invoke(this, req,res);
		//5.判断s是否为空
			if(s!=null){
				req.getRequestDispatcher(s).forward(req, res);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new RuntimeException();
		} 
	}
	public  String index(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			return null;
	}
	
}
