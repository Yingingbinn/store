package com.bin.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.bin.Converter.MyConverter;
import com.bin.constant.constant;
import com.bin.pojo.User;
import com.bin.service.UserService;
import com.bin.service.Impl.UserServiceImpl;
import com.bin.utils.MD5Utils;
import com.bin.utils.UUIDUtils;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends baseServlet {
	
	public  String  add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	public  String  registUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/jsp/register.jsp";
	}

	public  String  regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// TODO Auto-generated method stub
		//1,封装数据
		User user=new User();
		//注册自定义转换器
		ConvertUtils.register(new MyConverter(), Date.class);
		BeanUtils.populate(user, req.getParameterMap());
		//1.1设置用户id
		user.setUid(UUIDUtils.getId());
		//1.2设置激活码
		user.setCode(UUIDUtils.getCode());
		//1.3 md5加密密码
		user.setPassword(MD5Utils.md5(user.getPassword()));
		//2,调用service完成注册
		UserService userService =new UserServiceImpl();
		userService.regist(user);
		//3,页面转发
		req.setAttribute("msg", "用户注册成功请去邮箱激活！");
		return "/jsp/msg.jsp";
	}
	public  String  active(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		//获取激活码
		String code =req.getParameter("code");
		//调用service 完成激活
		UserService userService = new UserServiceImpl();
	   User user=  userService.active(code);
	   if(user==null){
		   req.setAttribute("msg", "激活失败请重新注册！");
	   }else{
		   req.setAttribute("msg", "激活成功，你现在可以登录了");
	   }
		// 跳转
		return "/jsp/msg.jsp";
	}
	public  String  loginUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "/jsp/login.jsp";
	}
	public  String  login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取用户名和密码
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		password=MD5Utils.md5(password);
		//调用service 完成登录操作  返回 user
		UserService userService=new UserServiceImpl();
		User user=userService.login(username,password);
		//判断用户
		if(user ==null){
			req.setAttribute("msg", "用户不存在，请重新登入！");
			return "/jsp/login.jsp";
		}else{
			if(constant.USER_ACTIVE!=user.getState()){
				req.setAttribute("msg", "用户未激活");
				return "/jsp/login.jsp";
			}
		}
		//将用户放入session中重定向
		req.getSession().setAttribute("user", user);
		resp.sendRedirect(req.getContextPath()+"/");
		return null;
	}
	public  String  loginout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 关闭session
	   req.getSession().invalidate();
		//重定向
	   resp.sendRedirect(req.getContextPath());
	   return null;
		//首页
	}

}
